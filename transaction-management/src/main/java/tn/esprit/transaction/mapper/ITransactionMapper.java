package tn.esprit.transaction.mapper;
import tn.esprit.transaction.dto.TransactionDto;
import tn.esprit.transaction.model.Transaction;
import java.util.List;

public interface ITransactionMapper {
    TransactionDto fromentityTodto(Transaction transaction);
    Transaction fromDtoToentity(TransactionDto transactionDto);

    List<TransactionDto> fromentityListTodtoList(List<Transaction> transactions);
    List<Transaction> fromDtoListToentityList(List<TransactionDto> transactionDtos);


}
