package tn.esprit.card_management.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
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
    @GeneratedValue(strategy = GenerationType.UUID)
    String numeroCard;
    LocalDateTime dateExpiration;
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
