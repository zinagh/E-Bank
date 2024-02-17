package com.mfbank.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "t_static")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Static {
    @Id
    private String reference;
    private Float msc;
    private Float roe;
    private Float dE;
    private Float ptr;
    private Float dcr;
    private Float dfl;
    // Generate a universally unique identifier (UUID) for robust uniqueness
    @PrePersist
    public void generateReference() {
        reference = UUID.randomUUID().toString();
    }
    @OneToOne(mappedBy="stat")
    private User user;


}
