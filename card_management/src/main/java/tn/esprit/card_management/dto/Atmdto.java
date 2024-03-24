package tn.esprit.card_management.dto;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Atmdto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int Id;
    String Location;
    float Somme;
    boolean Statut;
    String Recu;
}
