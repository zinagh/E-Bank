package tn.esprit.transaction.model;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

import java.util.UUID;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)

public class Invoice {
    @Id
    String reference;
    String statut;
    @OneToOne
    Transaction transaction;
    @PrePersist
    public void generateId(){
        this.reference= UUID.randomUUID().toString();
    }
}
