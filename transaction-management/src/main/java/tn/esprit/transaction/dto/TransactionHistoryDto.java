package tn.esprit.transaction.dto;
import java.util.Date;
import lombok.*;
import lombok.experimental.FieldDefaults;


@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)

public class TransactionHistoryDto {

    String reference;
    String source;
    String destination;
    float amount;
    Date date;
}
