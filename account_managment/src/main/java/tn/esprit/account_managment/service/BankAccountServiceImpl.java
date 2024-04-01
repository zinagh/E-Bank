package tn.esprit.account_managment.service;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.account_managment.dto.BankAccountDto;
import tn.esprit.account_managment.mapper.IBankAccountMapper;
import tn.esprit.account_managment.model.BankAccount;
import tn.esprit.account_managment.model.Fee;
import tn.esprit.account_managment.model.FeeType;
import tn.esprit.account_managment.repository.BankAccountRepository;
import tn.esprit.account_managment.repository.FeeRepository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BankAccountServiceImpl implements  IBankAccountService{
    @Autowired
    private final BankAccountRepository bankAccountRepository;
    @Autowired
    private final FeeRepository feeRepository;
    @Autowired
    private final IBankAccountMapper iBankAccountMapper;

    @Override
    public List<BankAccountDto> retrieveAllBankAccounts() {
        List<BankAccount> bankaccounts = bankAccountRepository.findAll();
        List<BankAccountDto> bankAccountDtos = iBankAccountMapper.toDtoList(bankaccounts);
         return bankAccountDtos;
    }


    @Override
    public BankAccountDto retrieveBankAccount(String bankAccountId) {
        BankAccount bankAccount =  bankAccountRepository.findById(bankAccountId).get();
        BankAccountDto bankAccountdto = iBankAccountMapper.toDto(bankAccount);
        return bankAccountdto;
    }

      @Override
    public void addBankAccount(BankAccountDto bankAccountDto) {
          BankAccount bankAccount = iBankAccountMapper.toEntity(bankAccountDto);
          String typeBankAccount = bankAccountDto.getType().toString();
           Fee fee = getFeeFromBankAccount(typeBankAccount);
           if(fee != null) {
               bankAccount.setDefaultFees(fee);
           }
           Date currentDate = new Date();
           bankAccount.setCreation_date(currentDate);
           bankAccount.setActivated(false);
           bankAccount.setNegativeSoldeAllowed(true);
           bankAccount.setNegativeSoldeDepassement(false);
           bankAccount.setNegativeSoldeAmount(200.0f);
        bankAccountRepository.save(bankAccount);
    }


    public Fee getFeeFromBankAccount(String typeBankAccount) {
        if(typeBankAccount.equals("currentAccount")){
            List<Fee> fees = feeRepository.findByFeeType(FeeType.TRANSACTION_FEE_currentAccount);
            if(!fees.isEmpty()){
                Fee fee = fees.get(0);
                return fee;
            }
        } else if (typeBankAccount.equals("savingsAccount")) {
            List<Fee> fees = feeRepository.findByFeeType(FeeType.TRANSACTION_FEE_savingsAccount);
            if(!fees.isEmpty()){
                Fee fee = fees.get(0);
                return fee;
            }
        }
        return null;
    }

    @Override
    public void removeBankAccount(String bankAccountId) {
        bankAccountRepository.deleteById(bankAccountId);
    }
    @Override
    public void modifyBankAccount(BankAccountDto bankAccountDto) {
        BankAccount bankAccount = iBankAccountMapper.toEntity(bankAccountDto);
         bankAccountRepository.save(bankAccount);
    }
}
