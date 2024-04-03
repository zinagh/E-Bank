package tn.esprit.transaction.service;
import tn.esprit.transaction.dto.TransactionHistoryDto;
import java.util.List;

public interface ITransactionHistoryService {
    public void addTransacationHistory(TransactionHistoryDto transactionhistoryDto);
    List<TransactionHistoryDto> getAllTransactionhistories();
    public TransactionHistoryDto getByTransactionHistoryId(String id);
    void deleteTransactionHistory(String id);
    public void modifyTransactionHistory(TransactionHistoryDto transactionhistoryDto);
}
