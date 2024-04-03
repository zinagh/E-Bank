package tn.esprit.transaction.mapper;
import org.springframework.stereotype.Service;
import tn.esprit.transaction.dto.TransactionDto;
import tn.esprit.transaction.dto.TransactionHistoryDto;
import tn.esprit.transaction.model.TransactionHistory;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service

public class TransactionHistoryMapper implements ITransactionHistoryMapper{
    @Override
    public TransactionHistoryDto fromentityTodto(TransactionHistory transactionhistory){

        TransactionHistoryDto transactionhistoryDto = new TransactionHistoryDto();
        transactionhistoryDto.setReference(transactionhistory.getReference());
        transactionhistoryDto.setSource(transactionhistory.getSource());
        transactionhistoryDto.setDestination(transactionhistory.getDestination());
        transactionhistoryDto.setAmount(transactionhistory.getAmount());
        transactionhistoryDto.setDate(transactionhistory.getDate());
        return transactionhistoryDto;
    }
    @Override
    public TransactionHistory fromDtoToentity(TransactionHistoryDto transactionhistoryDto){
        TransactionHistory transactionhistory = new TransactionHistory();
        transactionhistory.setReference(transactionhistoryDto.getReference());
        transactionhistory.setSource(transactionhistoryDto.getSource());
        transactionhistory.setDestination(transactionhistoryDto.getDestination());
        transactionhistory.setAmount(transactionhistoryDto.getAmount());
        transactionhistory.setDate(transactionhistoryDto.getDate());
        return transactionhistory;
    }

    @Override
    public TransactionHistoryDto fromTransactionDtoentityToTransactionHistoryDto(TransactionDto transactionDto){
        Date currentDate = new Date();
        TransactionHistoryDto transactionhistoryDto = new TransactionHistoryDto();
        transactionhistoryDto.setReference(transactionDto.getReference());
        transactionhistoryDto.setSource(transactionDto.getSource());
        transactionhistoryDto.setDestination(transactionDto.getDestination());
        transactionhistoryDto.setAmount(transactionDto.getMontant());
        transactionhistoryDto.setDate(currentDate);
        return transactionhistoryDto;
    }
    @Override
    public List<TransactionHistoryDto> fromentityListTodtoList(List<TransactionHistory> transactionhistories){
        return transactionhistories.stream()
                .map(this::fromentityTodto)
                .collect(Collectors.toList());

    }
    @Override
    public List<TransactionHistory> fromDtoListToentityList(List<TransactionHistoryDto> transactionhistoriesDtos){
        return transactionhistoriesDtos.stream()
                .map(this::fromDtoToentity)
                .collect(Collectors.toList());

    }
}
