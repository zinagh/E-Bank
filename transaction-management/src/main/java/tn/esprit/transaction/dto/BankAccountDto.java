package tn.esprit.transaction.dto;
import lombok.*;
import lombok.experimental.FieldDefaults;
import tn.esprit.transaction.model.UserAsStudentonBA;
import tn.esprit.transaction.model.TypeBankAccount;
import java.util.Date;

@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)

public class BankAccountDto {
     String reference;
     String titulaire;
     Double account_balance;
     Integer account_type;
     Date creation_date;
     Boolean activated;
     Date deactivation_date;
     Float account_limit;
     Float amount_fund;
     Integer code;
     TypeBankAccount type;
     Float prime_rates;
     Boolean negativeSoldeAllowed;
     Float negativeSoldeAmount;
     Boolean nagativeSoldeDepassement;
     Date negativeSoldeDepassement;
     UserAsStudentonBA userAsStudent;

}
