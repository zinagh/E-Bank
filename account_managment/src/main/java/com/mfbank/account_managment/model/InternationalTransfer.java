package com.mfbank.account_managment.model;

import jakarta.persistence.*;
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
public class InternationalTransfer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private boolean sendOrReceive;
    private String objectofTransaction;
    private double amount;
    private String currencyCode;
    private double fees;
    private boolean approval;
    private String employeeApprovalUsername;
    private String status;
    private Date date;


    @ManyToOne
    private BankAccount bankAccountToMakeTransfert;

    @ManyToOne
    private Fee internationnalFees;
}
