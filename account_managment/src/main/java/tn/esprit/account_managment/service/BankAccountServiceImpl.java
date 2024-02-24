package tn.esprit.account_managment.service;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.account_managment.dto.BankAccountDto;
import tn.esprit.account_managment.mapper.IBankAccountMapper;
import tn.esprit.account_managment.model.BankAccount;
import tn.esprit.account_managment.repository.BankAccountRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BankAccountServiceImpl implements  IBankAccountService{
    @Autowired
    private final BankAccountRepository bankAccountRepository;
    @Autowired
    private final IBankAccountMapper iBankAccountMapper;

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
    public void addBankAccount(BankAccountDto bankAccountDto) {
        BankAccount bankAccount = iBankAccountMapper.bankAccountDtoTobankAccount(bankAccountDto);
        bankAccountRepository.save(bankAccount);
    }
    @Override
    public void removeBankAccount(String bankAccountId) {
        bankAccountRepository.deleteById(bankAccountId);
    }
    @Override
    public void modifyBankAccount(BankAccountDto bankAccountDto) {
        BankAccount bankAccount = iBankAccountMapper.bankAccountDtoTobankAccount(bankAccountDto);
         bankAccountRepository.save(bankAccount);
    }
}
