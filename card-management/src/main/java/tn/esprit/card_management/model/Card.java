package tn.esprit.card_management.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String numeroCard;
    LocalDateTime dateExpiration;
    String codeSecurite;
    String titulaire;
    String NIP;
    String CVV;
    boolean activated;
    boolean disableCard; //expired
    float solde;
    float plafond;
    @Enumerated(EnumType.STRING)
    NomCardType typeC;
    @ManyToMany
    @JoinTable(name = "carte_atm",
            joinColumns = @JoinColumn(name = "numeroCard"),
            inverseJoinColumns = @JoinColumn(name = "id"))
    Set<Atm> atms = new HashSet<>();
    @OneToMany(mappedBy = "card", cascade = CascadeType.ALL, orphanRemoval = true)
    List<Reclamation> reclamations = new ArrayList<>();
    @OneToMany(mappedBy = "card", cascade = CascadeType.ALL, orphanRemoval = true)
    List<TransactionLog> transactions = new ArrayList<>();
    @ManyToOne(cascade = CascadeType.ALL)
    Fee assignedFee;

}
