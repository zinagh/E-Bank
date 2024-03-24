package tn.esprit.card_management.dto;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;
import lombok.experimental.FieldDefaults;
import tn.esprit.card_management.model.NomCardType;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Carddto {
    String numeroCard;
    LocalDateTime dateExpiration;
    String codeSecurite;
    String titulaire;
    String NIP;
    String CVV;
    boolean activated;
    boolean disableCard;
    float CommisionBasedOnAccount;
    float plafond;
    float solde;
    @Enumerated(EnumType.STRING)
    NomCardType typeC;

}
