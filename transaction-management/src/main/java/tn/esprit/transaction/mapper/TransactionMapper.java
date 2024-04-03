package tn.esprit.transaction.mapper;
import org.springframework.stereotype.Service;
import tn.esprit.transaction.dto.TransactionDto;
import tn.esprit.transaction.model.Transaction;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class TransactionMapper implements ITransactionMapper{
    @Override
    public TransactionDto fromentityTodto(Transaction transaction) {
        TransactionDto transactionDto = new TransactionDto();
        transactionDto.setReference(transaction.getReference());
        transactionDto.setSource(transaction.getSource());
        transactionDto.setDestination(transaction.getDestination());
        transactionDto.setMontant(transaction.getMontant());
        transactionDto.setDate_heure(transaction.getDate_heure());
        transactionDto.setType(transaction.getType());
        transactionDto.setDescription(transaction.getDescription());
        transactionDto.setValidation(transaction.isValidation());
        transactionDto.setCancelBysender(transaction.isCancelBysender());
        transactionDto.setCancelByreceiver(transaction.isCancelByreceiver());
        return transactionDto;
    }


    @Override
    public Transaction fromDtoToentity(TransactionDto transactionDto){
        Transaction transaction = new Transaction();
        transaction.setReference(transactionDto.getReference());
        transaction.setSource(transactionDto.getSource());
        transaction.setDestination(transactionDto.getDestination());
        transaction.setMontant(transactionDto.getMontant());
        transaction.setDate_heure(transactionDto.getDate_heure());
        transaction.setType(transactionDto.getType());
        transaction.setDescription(transactionDto.getDescription());
        transaction.setValidation(transactionDto.isValidation());
        transaction.setCancelBysender(transactionDto.isCancelBysender());
        transaction.setCancelByreceiver(transactionDto.isCancelByreceiver());
        return transaction;
    }


    @Override
    public List<TransactionDto> fromentityListTodtoList(List<Transaction> transactions){
        return transactions.stream()
                .map(this::fromentityTodto)
                .collect(Collectors.toList());
    }

    @Override
    public List<Transaction> fromDtoListToentityList(List<TransactionDto> transactionDtos){
            return transactionDtos.stream()
                    .map(this::fromDtoToentity)
                    .collect(Collectors.toList());
    }

}
