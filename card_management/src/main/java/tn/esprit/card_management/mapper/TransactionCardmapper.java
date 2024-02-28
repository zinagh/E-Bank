package tn.esprit.card_management.mapper;

import org.springframework.stereotype.Service;
import tn.esprit.card_management.dto.BankAccountForCarddto;
import tn.esprit.card_management.dto.TransactionCarddto;
import tn.esprit.card_management.model.BankAccountForCard;
import tn.esprit.card_management.model.TransactionCard;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransactionCardmapper implements ITransactionCardmapper {
    @Override
    public TransactionCarddto fromentityTodto(TransactionCard transactionCard) {
        TransactionCarddto transactionCarddto = new TransactionCarddto();
        transactionCarddto.setReference(transactionCard.getReference());
       /*transactionCarddto.setDestinationdto(transactionCard.getDestination());
        transactionCarddto.setSourcedto(transactionCard.getSource());*/
        transactionCarddto.setMontant(transactionCard.getMontant());
        transactionCarddto.setDate_heure(transactionCard.getDate_heure());
        transactionCarddto.setType(transactionCard.getType());
        transactionCarddto.setDescription(transactionCard.getDescription());
        transactionCarddto.setCancelByReceiver(transactionCard.isCancelByReceiver());
        transactionCarddto.setCancelBySender(transactionCard.isCancelBySender());
        transactionCarddto.setValidation(transactionCard.isValidation());

        return transactionCarddto;
    }
    @Override
    public TransactionCard fromdtoToentity(TransactionCarddto transactionCarddto) {
        TransactionCard transactionCard = new TransactionCard();
        transactionCard.setReference(transactionCarddto.getReference());
       transactionCard.setDestination(transactionCarddto.getDestinationdto());
        transactionCard.setSource(transactionCarddto.getSourcedto());
        transactionCard.setMontant(transactionCarddto.getMontant());
        transactionCard.setDate_heure(transactionCarddto.getDate_heure());
        transactionCard.setType(transactionCarddto.getType());
        transactionCard.setDescription(transactionCarddto.getDescription());
        transactionCard.setCancelByReceiver(transactionCarddto.isCancelByReceiver());
        transactionCard.setCancelBySender(transactionCarddto.isCancelBySender());
        transactionCard.setValidation(transactionCarddto.isValidation());

        return transactionCard;
    }
        @Override
        public List<TransactionCarddto>fromListentityTodtos (List< TransactionCard > transactionCards) {
            return transactionCards.stream()
                    .map(this::fromentityTodto)
                    .collect(Collectors.toList());
        }

        @Override
        public List<TransactionCard>fromListdtosToentities(List<TransactionCarddto> transactionCarddtos){
            return transactionCarddtos.stream()
                    .map(this::fromdtoToentity)
                    .collect(Collectors.toList());
        }
    }

