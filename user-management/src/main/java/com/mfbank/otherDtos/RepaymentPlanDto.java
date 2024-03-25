package com.mfbank.otherDtos;

import lombok.*;

import java.util.Date;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RepaymentPlanDto {
   private Long id;
   private Date dueDate ;
   private Double startingBalance ;
   private Double startingBalanceimbursementAount ;
   private  Double repaymentOfCcapital ;
   private Double interest ;
   private  Double remainingDueBalance;
   private Double interestAndCumulate ;
   private CreditDto creditDto;
}
