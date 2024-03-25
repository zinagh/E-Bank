package com.mfbank.otherDtos;

import lombok.*;

import java.util.Date;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PaymentDto {
    private Long id;
    private Double exactPayedAmount ;
    private PaymentType paymentType;
    private Date paymentDate ;
    private CreditDto creditDto;
}
