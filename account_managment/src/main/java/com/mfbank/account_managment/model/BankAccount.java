package com.mfbank.account_managment.model;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    @OneToMany(mappedBy = "bankAccountToMakeTransfert")
    private List<InternationalTransfer> internationalTransfers;
    @ManyToOne
    private Fee defaultFees;
}
