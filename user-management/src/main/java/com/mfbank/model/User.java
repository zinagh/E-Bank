package com.mfbank.model;

import com.mfbank.dto.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "t_user")
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    private String userName;
    private String nom;
    private String prenom;
    private String email;
    private Date dateNaissance;
    private Long cin;
    private Long numTel ;
    private Role role ;
    @ManyToMany( cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    private Set<Notification> notifications;

    @OneToOne
    private Static stat;
}
