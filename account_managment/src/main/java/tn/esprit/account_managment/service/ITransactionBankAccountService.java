package tn.esprit.account_managment.service;
import tn.esprit.account_managment.dto.TransactionBankAccountDto;
import tn.esprit.account_managment.model.TransactionBankAccount;
import java.util.List;

public interface ITransactionBankAccountService 
{
     List<TransactionBankAccountDto> retrieveAllTransactionBankAccounts();

     TransactionBankAccountDto retrieveTransactionBankAccount(String transactionBankAccountId);
     TransactionBankAccount addTransactionBankAccount(TransactionBankAccount t);
     void removeTransactionBankAccount(String transactionBankAccountId);
     TransactionBankAccount modifyTransactionBankAccount(TransactionBankAccount transactionBankAccount);
}
