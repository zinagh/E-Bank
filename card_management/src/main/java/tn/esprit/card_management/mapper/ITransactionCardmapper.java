package tn.esprit.card_management.mapper;

import tn.esprit.card_management.dto.TransactionCarddto;
import tn.esprit.card_management.model.TransactionCard;

import java.util.List;

public interface ITransactionCardmapper {
    TransactionCarddto fromentityTodto(TransactionCard transactionCard);
    TransactionCard fromdtoToentity(TransactionCarddto transactionCarddto);
    List<TransactionCarddto> fromListentityTodtos (List<TransactionCard> transactionCards);

    List<TransactionCard>fromListdtosToentities(List<TransactionCarddto> transactionCarddtos);
}
