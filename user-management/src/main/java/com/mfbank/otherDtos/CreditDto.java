package com.mfbank.otherDtos;

import lombok.*;

import java.util.Date;
import java.util.Set;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreditDto {
private  Long id ;
private Integer creditDuration ;
private Double creditAmount ;
private Date creditStartDate ;
private CreditStatus creditStatus;
private CreditType creditType;
private Double creditRate ;
private ContractDto contractDto;
private RepaymentPlanDto repaymentPlan;
private Set<PaymentDto> payments;
}
