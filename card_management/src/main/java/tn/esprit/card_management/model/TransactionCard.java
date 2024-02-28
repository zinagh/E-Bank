package tn.esprit.card_management.model;


import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TransactionCard {
    @Id
    String reference;
    @OneToOne
    BankAccountForCard destination;
    @OneToOne
    BankAccountForCard source;
    BigDecimal montant;
    LocalDateTime date_heure;
    String type ;
    String description;
    boolean validation;
    boolean CancelBySender;
    boolean CancelByReceiver;
    @ManyToOne
    @JoinColumn(name = "card_id")
    private Card card;


    @PrePersist
    public void prePersist() {this.reference = UUID.randomUUID().toString();}


}
