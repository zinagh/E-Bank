package tn.esprit.account_managment.service;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.account_managment.dto.BankAccountDto;
import tn.esprit.account_managment.mapper.IBankAccountMapper;
import tn.esprit.account_managment.model.BankAccount;
import tn.esprit.account_managment.repository.BankAccountRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@AllArgsConstructor
@NoArgsConstructor
public class BankAccountServiceImpl implements  IBankAccountService{
    BankAccountRepository bankAccountRepository;
    IBankAccountMapper iBankAccountMapper;

    @Override
    public List<BankAccountDto> retrieveAllBankAccounts() {
        List<BankAccount> bankaccounts = bankAccountRepository.findAll();
        List<BankAccountDto> bankAccountDtos = iBankAccountMapper.bankAccountsTobankAccountDtos(bankaccounts);
         return bankAccountDtos;
    }


    @Override
    public BankAccountDto retrieveBankAccount(String bankAccountId) {
        BankAccount bankAccount =  bankAccountRepository.findById(bankAccountId).get();
        BankAccountDto bankAccountdto = iBankAccountMapper.bankAccountTobankAccountDto(bankAccount);
       // bankAccountdto.setUserAsStudent(iUserMapper.userTouserdto(bankAccount.getUserasstudent()));
        return bankAccountdto;
    }

    @Override
    public void addBankAccount(BankAccountDto b) {
        BankAccount bankAccount = iBankAccountMapper.bankAccountDtoTobankAccount(b);
        bankAccountRepository.save(bankAccount);
    }
    @Override
    public void removeBankAccount(String bankAccountId) {
        bankAccountRepository.deleteById(bankAccountId);
    }
    @Override
    public BankAccount modifyBankAccount(BankAccount bankAccount) {
        return bankAccountRepository.save(bankAccount);
    }
}
