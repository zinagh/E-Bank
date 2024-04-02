package tn.esprit.card_management.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;
import tn.esprit.card_management.model.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
    float solde;
    float plafond;
    NomCardType typeC;
    Set<Atmdto> atms = new HashSet<>();
    List<Reclamationdto> reclamations = new ArrayList<>();
    List<TransactionLogdto> transactions = new ArrayList<>();
    Feedto assignedFee;
}
