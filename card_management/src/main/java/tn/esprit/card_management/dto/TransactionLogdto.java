package tn.esprit.card_management.dto;


import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TransactionLogdto {
    private Long id;
    private LocalDateTime transactionDate;
    private double amount;
    private String receiverId;
    private String receiverName;
    private String type;
}
