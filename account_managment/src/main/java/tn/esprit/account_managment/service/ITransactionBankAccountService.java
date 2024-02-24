package tn.esprit.account_managment.service;
import tn.esprit.account_managment.dto.TransactionBankAccountDto;
import tn.esprit.account_managment.model.TransactionBankAccount;
import java.util.List;

public interface ITransactionBankAccountService 
{
     List<TransactionBankAccountDto> retrieveAllTransactionBankAccounts();

     TransactionBankAccountDto retrieveTransactionBankAccount(String transactionBankAccountId);
     void addTransactionBankAccount(TransactionBankAccountDto transactionBankAccountDto, String destinationId,String sourceId);
     void removeTransactionBankAccount(String transactionBankAccountId);
     void modifyTransactionBankAccount(TransactionBankAccountDto transactionBankAccountDto);
}
