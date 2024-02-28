package tn.esprit.card_management.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Card {
    @Id
    String numeroCard;
    String dateExpiration;
    String codeSecurite;
    String titulaire;
    String NIP;
    String CVV;
    boolean activated;
    boolean disableCard;
    float limitSolde;
    float CommisionBasedOnAccount;
    float solde;
    @ManyToOne
     BankAccountForCard bankAccountForcard;
    @ManyToOne
     UserAsEmployee user;

    @OneToMany(mappedBy = "card", cascade = CascadeType.ALL)
    List<TransactionCard> transactions;
}
