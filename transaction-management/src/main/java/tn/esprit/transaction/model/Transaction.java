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
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Transaction {
    @Id
    String reference ;
    float montant;
    Date date_heure;
    String type;
    String description;
    boolean validation;
    boolean cancelBysender;
    boolean cancelByreceiver;
    String destination ;
    String source;
    @OneToOne
    Invoice invoice;
    @PrePersist
    public void generateId(){
        this.reference= UUID.randomUUID().toString();




}}
