package tn.esprit.card_management.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;


import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserAsEmployee {
    @Id
            @GeneratedValue(strategy =GenerationType.IDENTITY)
            Integer id;
    String userName;
    @OneToMany(mappedBy = "user")
    private List<Card> cards;
}




