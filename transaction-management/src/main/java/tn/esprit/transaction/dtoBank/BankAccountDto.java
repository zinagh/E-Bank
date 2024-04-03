package tn.esprit.transaction.dtoBank;

import lombok.*;

import tn.esprit.transaction.dtouser.Userdto;

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
    private List<InternationalTransferDto> internationalTransfers;
    private FeeDto defaultFees;

    private Userdto userdto;

}
