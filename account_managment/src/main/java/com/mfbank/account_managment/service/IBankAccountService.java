package com.mfbank.account_managment.service;
import com.mfbank.account_managment.dto.BankAccountDto;
import com.mfbank.account_managment.dto.InternationalTransferDto;

import java.util.List;

public interface IBankAccountService
{
    List<BankAccountDto> retrieveAllBankAccounts();
    BankAccountDto retrieveBankAccount(String bankAccountId);
    void addBankAccount(BankAccountDto bankAccountDto);
    void removeBankAccount(String bankAccountId);
    void modifyBankAccount(BankAccountDto bankAccountDto);
    BankAccountDto retrieveBankAccountByTitulaire(String bankAccountTitulaire);
    public Double retreiveAccountBalance(String bankAccountTitulaire);
    public List<InternationalTransferDto> findInternationalTransferByDateAndUserName(String username ,Integer monthF) ;
}
