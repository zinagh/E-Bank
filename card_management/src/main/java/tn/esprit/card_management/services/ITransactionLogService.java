package tn.esprit.card_management.services;

import tn.esprit.card_management.dto.TransactionLogdto;
import tn.esprit.card_management.model.TransactionLog;

import java.util.List;

public interface ITransactionLogService {
     List<TransactionLogdto> retrieveAllTransactionLogs();

     TransactionLog addTransactionLog(TransactionLogdto transactionLogdto);

     void removeTransactionLog(Long Id);

     TransactionLogdto retrieveTransactionLog(Long Id);

     TransactionLog modifyTransactionLog(TransactionLogdto transactionLogdto);
}
