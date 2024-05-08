package tn.esprit.card_management.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

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
    public class Fee {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        private String name;
        private String description;
        private double amount;
        @Enumerated(EnumType.STRING)
        private FeeType type;

        @OneToMany(mappedBy = "assignedFee", cascade = CascadeType.ALL, orphanRemoval = true)
        private List<Card> cards = new ArrayList<>();
}
