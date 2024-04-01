package tn.esprit.account_managment.dto;

import lombok.*;
import org.springframework.context.annotation.Lazy;

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
    private BankAccountDto bankAccountToMakeTransfert;
    private FeeDto internationnalFees;
}
