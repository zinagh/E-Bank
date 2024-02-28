package tn.esprit.card_management.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

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
    BankAccountForCarddto destinationdto;
    BankAccountForCarddto sourcedto;
    BigDecimal montant;
    LocalDateTime date_heure;
    String type ;
    String description;
    boolean validation;
    boolean CancelBySender;
    boolean CancelByReceiver;
    Carddto carddto;
}
