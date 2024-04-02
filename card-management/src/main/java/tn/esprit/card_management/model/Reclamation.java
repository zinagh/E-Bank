package tn.esprit.card_management.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Reclamation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long Id;
    String subject;
    String Description;
    LocalDateTime Date;
    @ManyToOne
    @JoinColumn(name = "numeroCard")
    Card card;
}
