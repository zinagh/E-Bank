package tn.esprit.transaction.service;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.transaction.dto.TransactionDto;
import tn.esprit.transaction.dto.TransactionHistoryDto;
import tn.esprit.transaction.mapper.ITransactionHistoryMapper;
import tn.esprit.transaction.model.Transaction;
import tn.esprit.transaction.model.TransactionHistory;
import tn.esprit.transaction.repository.TransactionHistoryRepository;
import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class TransactionHistoryService implements  ITransactionHistoryService{

    private final ITransactionHistoryMapper iTransactionHistoryMapper;
    private final TransactionHistoryRepository transactionhistoryReposiroty;
    @Override
    public void addTransacationHistory(TransactionHistoryDto transactionhistoryDto){
        TransactionHistory transactionhistory = iTransactionHistoryMapper.fromDtoToentity(transactionhistoryDto);
        transactionhistoryReposiroty.save(transactionhistory);
    }

    @Override
    public List<TransactionHistoryDto> getAllTransactionhistories(){
        List<TransactionHistory> transactionhistories = transactionhistoryReposiroty.findAll();
        List<TransactionHistoryDto> transactionhistoriesDtos = iTransactionHistoryMapper.fromentityListTodtoList(transactionhistories);
        return transactionhistoriesDtos;
    }
    public TransactionHistoryDto getByTransactionHistoryId(String id){
        Optional<TransactionHistory> optionnaltransactionhistory = transactionhistoryReposiroty.findById(id);
        if(optionnaltransactionhistory.isEmpty()){
            return  null;
        }
        TransactionHistory transactionhistory = optionnaltransactionhistory.get();
        TransactionHistoryDto transactionhistoryDto = iTransactionHistoryMapper.fromentityTodto(transactionhistory);
        return transactionhistoryDto;
    }

    public void deleteTransactionHistory(String id){
        Optional<TransactionHistory> optionnaltransactionhistory = transactionhistoryReposiroty.findById(id);
        if(optionnaltransactionhistory.isPresent()) {
            TransactionHistory transactionhistory = optionnaltransactionhistory.get();
            transactionhistoryReposiroty.delete(transactionhistory);
        }
    }
    @Override
    public void modifyTransactionHistory(TransactionHistoryDto transactionhistoryDto) {
        TransactionHistory transactionhistory = iTransactionHistoryMapper.fromDtoToentity(transactionhistoryDto);
        transactionhistoryReposiroty.save(transactionhistory);
    }

}
