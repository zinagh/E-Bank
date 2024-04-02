package com.mfbank.account_managment.dto;
import com.mfbank.account_managment.model.FeeType;
import lombok.*;


@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FeeDto {
    private Long id;
    private FeeType feeType;
    private double amountPercent;
    private String description;

}
