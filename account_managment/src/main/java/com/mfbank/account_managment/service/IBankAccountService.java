package com.mfbank.account_managment.service;
import com.mfbank.account_managment.dto.BankAccountDto;
import com.mfbank.account_managment.dto.QuestionDto;
import lombok.SneakyThrows;

import java.util.List;

public interface IBankAccountService
{
    QuestionDto addQuestion(QuestionDto questionD);

    List<QuestionDto> getQuestions();

    List<BankAccountDto> retrieveAllBankAccounts();
    BankAccountDto retrieveBankAccount(String bankAccountId);
    void addBankAccount(BankAccountDto bankAccountDto);
    void removeBankAccount(String bankAccountId);
    void modifyBankAccount(BankAccountDto bankAccountDto);
    Double retreiveAccountBalance(String bankAccountTitulaire);
    BankAccountDto retrieveBankAccountByTitulaire(String bankAccountTitulaire);

    @SneakyThrows
    QuestionDto generateQuestion(String topic, int numberOfQuestions);
}
