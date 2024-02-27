package tn.esprit.transaction.model;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import java.util.Date;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class BankAccount
{
    @Id
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
     Date negativeSoldeDepassementDay;
     UserAsStudentonBA userAsStudent;


}