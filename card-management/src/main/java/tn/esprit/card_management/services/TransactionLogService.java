package tn.esprit.card_management.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.card_management.dto.TransactionLogdto;
import tn.esprit.card_management.mapper.ITransactionLogmapper;
import tn.esprit.card_management.model.TransactionLog;
import tn.esprit.card_management.repository.TransactionLogRepository;

import java.util.List;
@Service
@RequiredArgsConstructor
public class TransactionLogService implements ITransactionLogService{
    private final ITransactionLogmapper iTransactionLogmapper;
    private final TransactionLogRepository TransactionLogRepository;
    @Override
    public List<TransactionLogdto> retrieveAllTransactionLogs() {
        List<TransactionLog> transactionLogs = TransactionLogRepository.findAll();
        List<TransactionLogdto> transactionLogdtos = iTransactionLogmapper.fromListentityTodtos(transactionLogs);
        return transactionLogdtos;    }

    @Override
    public TransactionLog addTransactionLog(TransactionLogdto transactionLogdto) {
        TransactionLog transactionLog = iTransactionLogmapper.dtoToEntity(transactionLogdto);
        TransactionLogRepository.save(transactionLog);
        return transactionLog;
    }

    @Override
    public void removeTransactionLog(Long Id) {
        TransactionLogRepository.deleteById(Id);


    }

    @Override
    public TransactionLogdto retrieveTransactionLog(Long Id) {
        TransactionLog transactionLog = TransactionLogRepository.findById(Id).get();
        return iTransactionLogmapper.entityToDto(transactionLog);
    }

    @Override
    public TransactionLog modifyTransactionLog(TransactionLogdto transactionLogdto) {
        TransactionLog transactionLog = iTransactionLogmapper.dtoToEntity(transactionLogdto);
        TransactionLogRepository.save(transactionLog);
        return transactionLog;
    }

}
