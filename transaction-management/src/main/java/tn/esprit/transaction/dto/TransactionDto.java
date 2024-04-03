package tn.esprit.transaction.dto;
import lombok.*;
import lombok.experimental.FieldDefaults;
import tn.esprit.transaction.dtoBank.BankAccountDto;

import java.util.Date;

@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)

public class TransactionDto {

    String reference ;
    String destination;
    String source;
    float montant;
    Date date_heure;
    String type;
    String description;
    boolean validation;
    boolean cancelBysender;
    boolean cancelByreceiver;
    BankAccountDto bankAccountDto;
    BankAccountDto bankAccountForRecieverDto;


}
