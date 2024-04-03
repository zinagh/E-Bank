package tn.esprit.transaction.dtoBank;
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
