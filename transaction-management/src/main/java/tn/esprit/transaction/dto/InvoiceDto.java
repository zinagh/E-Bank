package tn.esprit.transaction.dto;
import tn.esprit.transaction.model.Transaction;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class InvoiceDto {

    String reference;
    String statut;
    Transaction transaction;
}
