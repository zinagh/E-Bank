package tn.esprit.account_managment.service;
import tn.esprit.account_managment.dto.BankAccountDto;
import tn.esprit.account_managment.model.BankAccount;
import java.util.List;

public interface IBankAccountService
{
    List<BankAccountDto> retrieveAllBankAccounts();
    BankAccountDto retrieveBankAccount(String bankAccountId);
    void addBankAccount(BankAccountDto bankAccountDto);
    void removeBankAccount(String bankAccountId);
    void modifyBankAccount(BankAccountDto bankAccountDto);
}
