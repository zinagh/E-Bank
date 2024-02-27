package tn.esprit.transaction.dto;
import lombok.*;
import lombok.experimental.FieldDefaults;



@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)

public class BankAccountForCardDto {

    String reference;
    String titulaire;
    boolean activated;
    double account_balance;
    int account_type;
    int account_limit;
    float sentAmount;
}
