package tn.esprit.account_managment.dto;
import lombok.*;
import tn.esprit.account_managment.model.BankAccount;
import java.math.BigDecimal;
import java.util.Date;

@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TransactionBankAccountDto
{
    private String reference;
    private BankAccountDto destination;
    private BankAccountDto source;
    private BigDecimal montant;
    private Date date_heure;
    private String type;
    private String description;
    private Boolean validation;
    private Boolean cancelBysender;
    private Boolean cancelByreceiver;
}
