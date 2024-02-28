package tn.esprit.card_management.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.card_management.dto.TransactionCarddto;
import tn.esprit.card_management.mapper.ITransactionCardmapper;
import tn.esprit.card_management.model.TransactionCard;
import tn.esprit.card_management.repository.TransactionCardRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionCardService {
    private final ITransactionCardmapper iTransactionCardmapper;
    private final TransactionCardRepository transactionCardRepository;
    public void addTransactionCard(TransactionCarddto transactionCarddto) {
        TransactionCard transactionCard = iTransactionCardmapper.fromdtoToentity(transactionCarddto);
        transactionCardRepository.save(transactionCard);
    }

    public List<TransactionCard> retrieveall() {
        List<TransactionCard> transactionCards = transactionCardRepository.findAll();
        List<TransactionCarddto> transactionCarddtos = iTransactionCardmapper.fromListentityTodtos(transactionCards);
        return transactionCards;
    }
}
