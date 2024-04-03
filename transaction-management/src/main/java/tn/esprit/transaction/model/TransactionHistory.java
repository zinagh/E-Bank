package tn.esprit.transaction.model;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

import java.util.Date;
import java.util.UUID;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)

public class TransactionHistory {
    @Id
    String reference;
    float amount;
    Date date;
    String source ;
    String destination ;

    @PrePersist
    public void generateId(){
        this.reference= UUID.randomUUID().toString();

    }}
