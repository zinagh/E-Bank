package tn.esprit.card_management.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BankAccountForCard {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String reference;
    String titulaire;
    boolean activated;
    double account_balance;
    Integer account_limit;
    float sendamounts;
    @OneToMany(mappedBy = "bankAccountForcard")
    List<Card> cards;


}
