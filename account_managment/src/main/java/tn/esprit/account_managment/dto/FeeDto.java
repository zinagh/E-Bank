package tn.esprit.account_managment.dto;
import lombok.*;
import tn.esprit.account_managment.model.FeeType;


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
