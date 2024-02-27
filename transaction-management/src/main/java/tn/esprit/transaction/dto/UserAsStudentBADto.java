package tn.esprit.transaction.dto;
import java.util.Date;
import lombok.*;
import lombok.experimental.FieldDefaults;


@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)

public class UserAsStudentBADto {

    String username;
    String firstname;
    String lastname;
    String financial_informations;
    Date datesPaiement;
    double montantPaiement;
}
