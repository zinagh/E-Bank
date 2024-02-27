package tn.esprit.card_management.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;
import tn.esprit.card_management.model.Card;

import java.util.List;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BankAccountForCarddto {

    String reference;
    String titulaire;
    boolean activated;
    double account_balance;
    Integer account_limit;
    float sendamounts;
    List<Card> cards;
}
