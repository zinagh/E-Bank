package com.mfbank.controller;
import com.mfbank.configuration.KeycloakSecurity;
import com.mfbank.dto.Userdto;
import com.mfbank.mapper.Imapper;
import com.mfbank.model.User;
import com.mfbank.service.IUserService;
import jakarta.ws.rs.core.Response;
import lombok.AllArgsConstructor;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.representations.idm.RoleRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@RestController
public class UserController {
    @Autowired
    IUserService userService;
    @Autowired
    KeycloakSecurity keycloakSecurity;
    @Autowired
    Imapper imapper;
    @Value("${realm}")
    private String realm ;

    @PostMapping("/add-user")
    public Response addUser(@RequestBody Userdto u) {
        UserRepresentation userRepresentation = imapper.mapuserRep(u);
        System.out.println(userRepresentation);
        Keycloak keycloak =keycloakSecurity.getKeycloakInstance();
        Response response = keycloak.realm(realm).users().create(userRepresentation);
        if(response.getStatus() == 201) {
            String userId = keycloak.realm(realm).users().search(userRepresentation
                    .getUsername()).get(0).getId();
            imapper.assignerole(u.getRole().toString() ,userId);
            userService.addUser(u);
        }
        return Response.ok().build();
    }

  /*  @GetMapping("/retrieve-allKeycloak-users")
    public List<UserRepresentation> retrieveAllKeycloakUsers() {
        Keycloak keycloak =keycloakSecurity.getKeycloakInstance();
        List<UserRepresentation> users = keycloak.realm(realm).users().list();
        return users;
    } */


    @GetMapping("/retrieve-all-users")
    public List<Userdto> retrieveAllUsers() {
        List<Userdto> listUserdtos = userService.retrieveAllUsers();
        return listUserdtos;
    }

    @GetMapping("/retrieve-user/{userName}")
    public Userdto retrieveUser(@PathVariable("userName") String userName) {
        Userdto userdto = userService.retrieveUser(userName);
        return userdto;
    }


    @DeleteMapping("/remove-user/{userName}")
    public void removeUser(@PathVariable("userName") String userName) {
        userService.removeUser(userName);
    }

    @PutMapping("/modify-user")
    public User modifyUser(@RequestBody Userdto u) {
        User user = userService.modifyUser(u);
        return user;
    }

}
