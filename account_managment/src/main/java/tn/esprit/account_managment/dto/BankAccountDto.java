package tn.esprit.account_managment.dto;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.*;
import org.springframework.context.annotation.Lazy;
import tn.esprit.account_managment.model.Fee;
import tn.esprit.account_managment.model.InternationalTransfer;
import tn.esprit.account_managment.model.TypeBankAccount;

import java.util.Date;
import java.util.List;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BankAccountDto {
    private String accountNumber;
    private String titulaire;
    private String employeeUsername;
    private Double account_balance;
    private Date creation_date;
    private Boolean activated;
    private Date deactivation_date;
    private TypeBankAccount type;
    private Boolean negativeSoldeAllowed;
    private Float negativeSoldeAmount;
    private Boolean negativeSoldeDepassement;
    private Date negativeSoldeDepassementDay;
    private Date datetransaction;
    //
    private List<InternationalTransferDto> internationalTransfers;
    private FeeDto defaultFees;
}
