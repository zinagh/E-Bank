package tn.esprit.transaction.dto;
import tn.esprit.transaction.model.BankAccount;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)

public class TransactionBankAccountDto {
    String reference;
    BankAccount destination;
    BankAccount source;
    float montant;
    Date date_heure;
    String type;
    String description;
    boolean validation;
    boolean cancelBysender;
    boolean cancelByreceiver;
}
