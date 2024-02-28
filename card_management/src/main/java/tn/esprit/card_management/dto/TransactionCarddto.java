package tn.esprit.card_management.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;
import tn.esprit.card_management.model.BankAccountForCard;

import java.math.BigDecimal;
import java.time.LocalDateTime;
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)

public class TransactionCarddto {
    String reference;
    BankAccountForCard destinationdto;
    BankAccountForCard sourcedto;
    BigDecimal montant;
    LocalDateTime date_heure;
    String type ;
    String description;
    boolean validation;
    boolean CancelBySender;
    boolean CancelByReceiver;
    Carddto carddto;
}
