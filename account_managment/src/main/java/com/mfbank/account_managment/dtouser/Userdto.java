package com.mfbank.account_managment.dtouser;
import lombok.*;

import java.util.Date;
import java.util.Set;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Userdto {
    private String userName;
    private String password;
    private String nom;
    private String prenom;
    private String email;
    private Date dateNaissance;
    private Long cin;
    private Long numTel ;
    private Role role ;
    private Set<Notificationdto> notifications;
}
