package com.mfbank.account_managment.model;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;
import java.util.Random;

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


    @PrePersist
    public void prePersist() {
        if (accountNumber == null || accountNumber.isEmpty()) {
            accountNumber = generateRandomAccountNumber();
        }
    }

    private String generateRandomAccountNumber() {
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 17; i++) {
            sb.append(random.nextInt(10));
        }
        return sb.toString();
    }

}
