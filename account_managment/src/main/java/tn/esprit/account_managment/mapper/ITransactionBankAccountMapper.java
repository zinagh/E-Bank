package tn.esprit.account_managment.mapper;

import tn.esprit.account_managment.dto.TransactionBankAccountDto;
import tn.esprit.account_managment.model.TransactionBankAccount;

import java.util.List;

public interface ITransactionBankAccountMapper
{
    TransactionBankAccountDto transactionttodto(TransactionBankAccount transactionBankAccount);
    TransactionBankAccount dtototransaction(TransactionBankAccountDto transactionBankAccountDto);

    List<TransactionBankAccountDto> transactionBankAccountsTotransactionBankAccountsDtos(List<TransactionBankAccount> transactionBankAccounts);

    List<TransactionBankAccount> transactionBankAccountsDtosTotransactionBankAccounts(List<TransactionBankAccountDto> transactionBankAccountDtos);
}
