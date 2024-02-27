package tn.esprit.transaction.model;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

import java.util.Date;




@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Transaction {
    @Id
    String reference ;
    BankAccount destination;
    BankAccount source;
    float montant;
    Date date_heure;
    String type;
    String description;
    boolean validation;
    boolean cancelBysender;
    boolean cancelByreceiver;

   // @ManyToOne



}
