package tn.esprit.card_management.model;


import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

import java.math.BigDecimal;
import java.time.DateTimeException;
import java.time.LocalDateTime;

public class TransactionCard {
    @Id
    String reference;
    BankAccountForCard destination;
    BankAccountForCard source;
    BigDecimal montant;
    LocalDateTime date_heure;
    String type ;
    String description;
    boolean validation;
    boolean CancelBySender;
    boolean CancelByReceiver;

    @ManyToOne
    private Card cards;
}
