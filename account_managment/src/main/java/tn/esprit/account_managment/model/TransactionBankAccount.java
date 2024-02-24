package tn.esprit.account_managment.model;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TransactionBankAccount
{
    @Id
    private String reference;
    private BigDecimal montant;
    private Date date_heure;
    private String type;
    private String description;
    private Boolean validation;
    private Boolean cancelBysender;
    private Boolean cancelByreceiver;

    @ManyToOne
    private BankAccount destination;

    @ManyToOne
    private BankAccount source;


    @PrePersist
    public void generateReference() {reference = UUID.randomUUID().toString();}
}
