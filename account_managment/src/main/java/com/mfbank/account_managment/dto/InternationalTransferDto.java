package com.mfbank.account_managment.dto;

import lombok.*;

import java.util.Date;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class InternationalTransferDto {
    private Long id;
    private boolean sendOrReceive;
    private String objectofTransaction;
    private double amount;
    private String currencyCode;
    private double fees;
    private boolean approval;
    private String employeeApprovalUsername;
    private String status;
    private String bankAccountToMakeTransfert;
    private FeeDto internationnalFees;
    private Date date;
}
