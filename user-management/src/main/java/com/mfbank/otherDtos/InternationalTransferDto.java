package com.mfbank.otherDtos;

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
    private Date date;
    private String objectofTransaction;
    private double amount;
    private String currencyCode;
    private double fees;
    private boolean approval;
    private String employeeApprovalUsername;
    private String status;
    private BankAccountDto bankAccountToMakeTransfert;
    private FeeDto internationnalFees;

}
