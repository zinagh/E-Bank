package tn.esprit.transaction.mapper;
import tn.esprit.transaction.dto.TransactionDto;
import tn.esprit.transaction.dto.TransactionHistoryDto;
import tn.esprit.transaction.model.TransactionHistory;
import java.util.List;

public interface ITransactionHistoryMapper {
    TransactionHistoryDto fromentityTodto(TransactionHistory transactionhistory);
    TransactionHistory fromDtoToentity(TransactionHistoryDto transactionhistoryDto);

    List<TransactionHistoryDto> fromentityListTodtoList(List<TransactionHistory> transactionhistories);
    List<TransactionHistory> fromDtoListToentityList(List<TransactionHistoryDto> transactionhistoriesDtos);
    TransactionHistoryDto fromTransactionDtoentityToTransactionHistoryDto(TransactionDto transactionDto);
}
