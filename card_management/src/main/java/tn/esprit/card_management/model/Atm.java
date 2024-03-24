package tn.esprit.card_management.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Atm {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int Id;
    String Location;
    float Somme;
    boolean Statut;
    String Recu;
    @ManyToMany(mappedBy = "Atm")
     Set<Card> cartes = new HashSet<>();

}
