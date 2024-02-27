package tn.esprit.transaction.service;
import tn.esprit.transaction.model.Transaction;

public interface ITransactionService {
        public Transaction validate(Transaction transaction);
        public boolean cancel(String reference);
        public Transaction record(Transaction transaction);
        public void applyFeesService(Transaction transaction);
        public void applyFeesTransaction(Transaction transaction);
    }


