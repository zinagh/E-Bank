package com.mfbank.service;
import com.mfbank.configuration.KeycloakSecurity;
import com.mfbank.dto.Userdto;
import com.mfbank.mapper.Imapper;
import com.mfbank.mapper.Usermapper;
import com.mfbank.model.Notification;
import com.mfbank.model.User;
import com.mfbank.repository.UserRepository;
import jakarta.ws.rs.core.Response;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
@Service
@RequiredArgsConstructor
public class UserServiceImp  implements IUserService{
    @Autowired
    Imapper imapper;
    @Autowired
    UserRepository userRepository;
    @Autowired
    Usermapper usermapper;
    @Autowired
    KeycloakSecurity keycloakSecurity;
    @Value("${realm}")
    private String realm;
    public List<Userdto> retrieveAllUsers() {
     List<User>  users =userRepository.findAll();
     List<Userdto> userdtos = usermapper.usersTouserdtos(users);
     return userdtos;
    }
    public Userdto retrieveUser(String userName) {
        User user=  userRepository.findById(userName).get();
        Userdto userdto = usermapper.userTouserdto(user);
        return userdto;
    }
    public void addUser(Userdto userdto ) {
        UserRepresentation userRepresentation = imapper.mapuserRep(userdto);
        System.out.println(userRepresentation);
        Keycloak keycloak =keycloakSecurity.getKeycloakInstance();
        Response response = keycloak.realm(realm).users().create(userRepresentation);
        if(response.getStatus() == 201) {
            String userId = keycloak.realm(realm).users()
                    .search(userRepresentation
                    .getUsername()).get(0).getId();
            imapper.assignerole(userdto.getRole().toString() ,userId);
            User user= usermapper.userdtoTouser(userdto) ;
            userRepository.save(user);
        }
    }
    public void removeUser(String userName) {
        Keycloak keycloak = keycloakSecurity.getKeycloakInstance();
        List<UserRepresentation> userRepresentations = keycloak.realm(realm).users().search(userName);
        String id = userRepresentations.get(0).getId();
        keycloak.realm(realm).users().delete(id);
        userRepository.deleteById(userName);
    }
    public User modifyUser(Userdto userdto) {
        Keycloak keycloak=keycloakSecurity.getKeycloakInstance();
        List<UserRepresentation>  userRepresentations=keycloak
                .realm(realm).users()
                .search(userdto.getUserName());
        UserRepresentation userToUpdate= imapper.mapuserRepToUpdate(userdto);
        String id=userRepresentations.get(0).getId();
        keycloak.realm(realm).users().get(id).update(userToUpdate);
        User user = usermapper.userdtoTouser(userdto);
        userRepository.save(user);
        return user;
    }
    public String updatepassword(String username ,String newpass ,String verifpass){
        if (newpass.equals(verifpass)) {
            Keycloak keycloak = keycloakSecurity.getKeycloakInstance();
            List<UserRepresentation> userRepresentations = keycloak.realm(realm).users().search(username);
            UserRepresentation userRepresentation = userRepresentations.get(0);
            List<CredentialRepresentation> creds = new ArrayList<>();
            CredentialRepresentation cred = new CredentialRepresentation();
            cred.setTemporary(false);
            cred.setValue(newpass);
            creds.add(cred);
            userRepresentation.setCredentials(creds);
            userRepresentation.setRequiredActions(Collections.emptyList());
            keycloak.realm(realm).users().get(userRepresentation.getId()).update(userRepresentation);
return "password is updated";
        } return "comparaison failed";
    }


}
