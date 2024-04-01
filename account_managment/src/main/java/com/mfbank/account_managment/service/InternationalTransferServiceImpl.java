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

import java.util.List;
import java.util.Optional;

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
        String bankAccountId = internationalTransferDto.getBankAccountToMakeTransfert().getAccountNumber();
        Optional<BankAccount> bankAccount = bankAccountRepository.findById(bankAccountId);
        if(bankAccount.isPresent()){
            BankAccount bankAccountToadd = bankAccount.get();
            internationalTransfer.setBankAccountToMakeTransfert(bankAccountToadd);
        }
        List<Fee> fees = feeRepository.findByFeeType(FeeType.CURRENCY_CONVERSION_FEE);
        if(!fees.isEmpty()){
            Fee fee = fees.get(0);
            internationalTransfer.setInternationnalFees(fee);
            internationalTransfer.setAmount(fee.getAmountPercent());
        }
        internationalTransfer.setApproval(false);
        internationalTransferRepository.save(internationalTransfer);
    }


    public void approveinternationaltransfer(String employeeApprovalUsername, Long id)
    {   Optional<InternationalTransfer> optionalInternationalTransfer = internationalTransferRepository.findById(id);
       if(optionalInternationalTransfer.isPresent()){
           InternationalTransfer internationalTransfer = optionalInternationalTransfer.get();
           internationalTransfer.setEmployeeApprovalUsername(employeeApprovalUsername);
           internationalTransfer.setApproval(true);
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
    public void removeInternationalTransfer(Long internationalTransferId) {
        internationalTransferRepository.deleteById(internationalTransferId);
    }
    @Override
    public void modifyInternationalTransfer(InternationalTransferDto internationalTransferDto) {
        InternationalTransfer internationalTransfer = iInternationalTransferMapper.toEntity(internationalTransferDto);
        internationalTransferRepository.save(internationalTransfer);
    }
}
