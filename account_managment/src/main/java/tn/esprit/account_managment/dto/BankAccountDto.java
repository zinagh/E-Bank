package tn.esprit.account_managment.dto;
import lombok.*;
import tn.esprit.account_managment.model.UserAsStudentonBA;

import java.util.Date;

@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class BankAccountDto
{
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
    private UserAsStudentonBADto userAsStudent;
}
