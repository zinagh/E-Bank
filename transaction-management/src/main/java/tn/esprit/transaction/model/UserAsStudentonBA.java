package tn.esprit.transaction.model;
import java.util.Date;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;





@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)

public class UserAsStudentonBA {
    @Id
    String username;
    String firstname;
    String lastname;
    String financial_informations;
    Date datesPaiement;
    double montantPaiement;
}
