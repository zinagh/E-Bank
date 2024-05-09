package com.mfbank.account_managment.service;

import com.mfbank.account_managment.repository.BankAccountRepository;
import com.mfbank.account_managment.repository.FeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.mfbank.account_managment.dto.InternationalTransferDto;
import com.mfbank.account_managment.mapper.IInternationalTransferMapper;
import com.mfbank.account_managment.model.BankAccount;
import com.mfbank.account_managment.model.Fee;
import com.mfbank.account_managment.model.FeeType;
import com.mfbank.account_managment.model.InternationalTransfer;
import com.mfbank.account_managment.repository.InternationalTransferRepository;

import java.util.*;

@Service
@RequiredArgsConstructor
public class InternationalTransferServiceImpl implements IInternationalTransferService
{
    @Autowired
    private final InternationalTransferRepository internationalTransferRepository;
    @Autowired
    private final IInternationalTransferMapper iInternationalTransferMapper;
    @Autowired
    private final FeeRepository feeRepository;
    @Autowired
    private final BankAccountRepository bankAccountRepository;



    @Override
    public List<InternationalTransferDto> retrieveAllInternationalTransfers() {
        List<InternationalTransfer> internationalTransfers = internationalTransferRepository.findAll();
        List<InternationalTransferDto> internationalTransferDtos = iInternationalTransferMapper.toDtoList(internationalTransfers);
        return internationalTransferDtos;
    }


    @Override
    public InternationalTransferDto retrieveInternationalTransfer(Long internationalTransferId) {
        InternationalTransfer internationalTransfer =  internationalTransferRepository.findById(internationalTransferId).get();
        InternationalTransferDto internationalTransferdto = iInternationalTransferMapper.toDto(internationalTransfer);
        return internationalTransferdto;
    }

    @Override
    public void addInternationalTransfer(InternationalTransferDto internationalTransferDto)    {
        InternationalTransfer internationalTransfer = iInternationalTransferMapper.toEntity(internationalTransferDto);
        String bankAccountId = internationalTransferDto.getBankAccountToMakeTransfert();
        System.out.println("bankAccountId is :" + bankAccountId);
        Optional<BankAccount> bankAccount = bankAccountRepository.findByAccountNumber(bankAccountId);
        System.out.println("bankAccount: " + bankAccount);
        if(bankAccount.isPresent()){
            BankAccount bankAccountToadd = bankAccount.get();
            internationalTransfer.setBankAccountToMakeTransfert(bankAccountToadd);
        }
        List<Fee> fees = feeRepository.findByFeeType(FeeType.CURRENCY_CONVERSION_FEE);
        if(!fees.isEmpty()){
            Fee fee = fees.get(0);
            internationalTransfer.setInternationnalFees(fee);
        }
        double calculatedFees = calculateTransactionFees(internationalTransfer.getAmount(), internationalTransfer.getCurrencyCode());
        internationalTransfer.setFees(calculatedFees);
        internationalTransfer.setApproval(false);
        internationalTransferRepository.save(internationalTransfer);
    }


    public void approveinternationaltransfer(String employeeApprovalUsername, Long id)
    {   Optional<InternationalTransfer> optionalInternationalTransfer = internationalTransferRepository.findById(id);
       if(optionalInternationalTransfer.isPresent()){
           InternationalTransfer internationalTransfer = optionalInternationalTransfer.get();
           internationalTransfer.setEmployeeApprovalUsername(employeeApprovalUsername);
           internationalTransfer.setApproval(true);
           internationalTransfer.setStatus("Approved");
           internationalTransferRepository.save(internationalTransfer);
           Optional<BankAccount> optionalBankAccount = bankAccountRepository.findByInternationalTransferId(internationalTransfer.getId());
           if(optionalBankAccount.isPresent()) {
               BankAccount bankAccount = optionalBankAccount.get();
               if(internationalTransfer.isSendOrReceive() == false){
                   bankAccount.setAccount_balance(bankAccount.getAccount_balance() - internationalTransfer.getAmount());
               } else if (internationalTransfer.isSendOrReceive() == true){
                   bankAccount.setAccount_balance(bankAccount.getAccount_balance() + internationalTransfer.getAmount());
               }
               bankAccountRepository.save(bankAccount);
           }
       }
    }

    @Override
    public List<Float> getstatisticsForChart (String bankAccountId, int month){
        Optional<BankAccount> optionalbankAccount = bankAccountRepository.findById(bankAccountId);
        if(optionalbankAccount.isEmpty()) {
            List<Float> nostat = new ArrayList<>(Arrays.asList(0.0f, 0.0f));
            return nostat;
        }
        BankAccount bankAccount = optionalbankAccount.get();
        List<InternationalTransfer> internationalTransfers = internationalTransferRepository.findByBankAccountAndMonth(bankAccount, month);
        List<Float> statistics = new ArrayList<>();
        int received = 0;
        int send = 0;
        int total = internationalTransfers.size();
        if(total == 0) {
            List<Float> nostat = new ArrayList<>(Arrays.asList(0.0f, 0.0f));
            return nostat;
        }
        for (InternationalTransfer internationalTransfer: internationalTransfers) {
            if(!internationalTransfer.isSendOrReceive()) {
                send++;
            }
            if(internationalTransfer.isSendOrReceive()) {
                received++;
            }
        }

        float receivedPercent = ((float) received / total) * 100;
        float sendPercent = ((float) send / total) * 100;
        statistics.add(receivedPercent);
        statistics.add(sendPercent);
        return statistics;
    }

    @Override
    public void removeInternationalTransfer(Long internationalTransferId) {
        internationalTransferRepository.deleteById(internationalTransferId);
    }
    @Override
    public void modifyInternationalTransfer(InternationalTransferDto internationalTransferDto) {
        InternationalTransfer internationalTransfer = iInternationalTransferMapper.toEntity(internationalTransferDto);
        internationalTransferRepository.save(internationalTransfer);
    }
    private double calculateTransactionFees(double amount, String currencyCode) {
        double fee = 0.0;

        if (currencyCode.equals("USD")) {
            if (amount < 1000) {
                fee = 5.0;
            } else {
                fee = amount * 0.01;
            }
        } else if (currencyCode.equals("EUR")) {
            if (amount < 800) {
                fee = 3.0;
            } else {
                fee = amount * 0.005;
            }
        } else {
            fee = amount * 0.02;
        }

        return fee;
    }


    @Override
    public List<InternationalTransferDto> retrieveAllInternationalTransfersByTitulaireAccount(String username) {
        Optional<BankAccount> opBankAccount = bankAccountRepository.findByTitulaire(username);
        if(opBankAccount.isEmpty()){
            return null;
        }
        BankAccount bankAccount = opBankAccount.get();
        List<InternationalTransfer> internationalTransfers = internationalTransferRepository.findByBankAccountToMakeTransfert(bankAccount);
        List<InternationalTransferDto> internationalTransferDtos = iInternationalTransferMapper.toDtoList(internationalTransfers);
        return internationalTransferDtos;
    }

}
