package tn.esprit.card_management.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;
import tn.esprit.card_management.model.BankAccountForCard;
import tn.esprit.card_management.model.TransactionCard;
import tn.esprit.card_management.model.UserAsEmployee;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Carddto {
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
    BankAccountForCarddto bankAccountForCarddto;
    UserAsEmployeedto userAsEmployeedto;
    List<TransactionCarddto> transactionsCarddtos;
}
