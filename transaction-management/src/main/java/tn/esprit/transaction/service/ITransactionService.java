package tn.esprit.transaction.service;
import tn.esprit.transaction.dto.TransactionDto;
import tn.esprit.transaction.model.Transaction;

import java.util.List;

public interface ITransactionService {
        Transaction addTransacation(TransactionDto transactionDto);

        List<TransactionDto> getAllTransactions();

        TransactionDto getByTransactionId(String id);
        void deleteTransaction(String id);
        void modifyTransaction(TransactionDto transactionDto);
        public List<TransactionDto> searchTransactions(String keyword);
        String getEmail();

        }


