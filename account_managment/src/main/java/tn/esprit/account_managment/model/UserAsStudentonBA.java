package tn.esprit.account_managment.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserAsStudentonBA
{
    @Id
    private String userName;
    private String firstname;
    private String lastname;
    private String financial_informations;
    private Date datesPaiments;
    private Double montantsPaiment;

    @OneToOne
    private BankAccount bankaccount;

}
