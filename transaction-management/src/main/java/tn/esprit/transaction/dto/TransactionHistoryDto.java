package tn.esprit.transaction.dto;
import tn.esprit.transaction.model.BankAccount;
import java.util.Date;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)

public class TransactionHistoryDto {

    String reference;
    BankAccount sender;
    BankAccount receiver;
    float amount;
    Date date;
}
