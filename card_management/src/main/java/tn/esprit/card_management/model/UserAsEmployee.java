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

    String userName;
    List<Card> cardsToManage;

    @OneToMany(mappedBy = "user")
    private List<Card> cards;
}




