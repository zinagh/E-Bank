package com.mfbank.mapper;

import com.mfbank.configuration.KeycloakSecurity;
import com.mfbank.dto.Role;
import com.mfbank.dto.Userdto;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.RoleRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class UMapper implements Imapper{
    @Autowired
    KeycloakSecurity keycloakSecurity;
    @Value("${realm}")
    private String realm;
    @Override
    public UserRepresentation mapuserRep (Userdto userdto) {
        UserRepresentation userRepresentation= new UserRepresentation();
        userRepresentation.setUsername(userdto.getUserName());
        userRepresentation.setEmail(userdto.getEmail());
        userRepresentation.setFirstName(userdto.getPrenom());
        userRepresentation.setLastName(userdto.getNom());
        userRepresentation.setEnabled(true);
        userRepresentation.setEmailVerified(false);
        List<CredentialRepresentation> creds= new ArrayList<>();
        CredentialRepresentation cred = new CredentialRepresentation();
        cred.setTemporary(true);
        cred.setValue(userdto.getPassword());
        creds.add(cred);
        userRepresentation.setCredentials(creds);
        userRepresentation.setRequiredActions(Collections.singletonList("UPDATE_PASSWORD"));
        return  userRepresentation;
    }

    @Override
    public void assignerole(String role , String id){
        Keycloak keycloak= keycloakSecurity.getKeycloakInstance();
        List<RoleRepresentation> roleRepresentations=new ArrayList<>();
        RoleRepresentation roleRepresentation=keycloak.realm(realm).roles().get(role).toRepresentation();
        roleRepresentations.add(roleRepresentation);
        keycloak.realm(realm).users().get(id).roles().realmLevel().add(roleRepresentations);
    }

}
