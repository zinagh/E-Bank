package tn.esprit.account_managment.model;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import tn.esprit.account_managment.dto.TypeBankAccount;

import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BankAccount
{
    @Id
    private String reference;
    private String titulaire;
    private Double account_balance;
    private Integer account_type;
    private Date creation_date;
    private Boolean activated;
    private Date deactivation_date;
    private Float account_limit;
    private Float amount_fund;
    private Integer code;
    private TypeBankAccount type;
    private Float prime_rates;
    private Boolean negativeSoldeAllowed;
    private Float negativeSoldeAmount;
    private Boolean negativeSoldeDepassement;
    private Date negativeSoldeDepassementDay;



    @OneToOne(mappedBy = "bankaccount")
    private UserAsStudentonBA userasstudent;

    @OneToMany(mappedBy = "destination")
    private List<TransactionBankAccount> ReceivedTransactions;

    @OneToMany(mappedBy = "source")
    private List<TransactionBankAccount> SentTransactions;



}
