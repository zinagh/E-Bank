package com.mfbank.account_managment.service;
import com.mfbank.account_managment.dto.BankAccountDto;

import java.util.List;

public interface IBankAccountService
{
    List<BankAccountDto> retrieveAllBankAccounts();
    BankAccountDto retrieveBankAccount(String bankAccountId);
    void addBankAccount(BankAccountDto bankAccountDto);
    void removeBankAccount(String bankAccountId);
    void modifyBankAccount(BankAccountDto bankAccountDto);
}
