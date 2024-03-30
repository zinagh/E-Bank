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
public class Reclamationdto {
    Long Id;
    String subject;
    String Description;
    LocalDateTime Date;
}
