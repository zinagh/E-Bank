package tn.esprit.card_management.mapper;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.card_management.dto.Feedto;
import tn.esprit.card_management.dto.TransactionLogdto;
import tn.esprit.card_management.model.Fee;
import tn.esprit.card_management.model.TransactionLog;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor

public class TransactionLogmapper implements  ITransactionLogmapper{

    @Autowired
    private final ModelMapper modelMapper;

    @Override
    public TransactionLog dtoToEntity(TransactionLogdto transactionLogdto) {
        return modelMapper.map(transactionLogdto, TransactionLog.class);
    }
    @Override
    public TransactionLogdto entityToDto(TransactionLog transactionLog) {
        return modelMapper.map(transactionLog, TransactionLogdto.class);
    }


    @Override
    public List<TransactionLogdto> fromListentityTodtos(List<TransactionLog> transactionLogs) {
        return transactionLogs.stream()
                .map(this::entityToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<TransactionLog> fromListdtosToentities(List<TransactionLogdto> transactionLogdtos) {
        return transactionLogdtos.stream()
                .map(this::dtoToEntity)
                .collect(Collectors.toList());
    }

}
