package tn.esprit.card_management.mapper;

import tn.esprit.card_management.dto.TransactionLogdto;
import tn.esprit.card_management.model.TransactionLog;

import java.util.List;
import java.util.stream.Collectors;

public interface ITransactionLogmapper {
     TransactionLog dtoToEntity(TransactionLogdto transactionLogdto) ;
     TransactionLogdto entityToDto(TransactionLog transactionLog) ;


     List<TransactionLogdto> fromListentityTodtos(List<TransactionLog> transactionLogs);

     List<TransactionLog> fromListdtosToentities(List<TransactionLogdto> transactionLogdtos);
}
