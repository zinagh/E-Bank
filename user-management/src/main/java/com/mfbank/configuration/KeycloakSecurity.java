package com.mfbank.configuration;

import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class KeycloakSecurity {
    Keycloak keycloak;
    @Value("${realm}")
    private String realm ;
    @Value("${client-id}")
    private String clientId ;
    @Value("${server-url}")
    private String serverUrl ;
    @Value("${grant-type}")
    private String grantType ;
    @Value("${name}")
    private String username ;
    @Value("${password}")
    private String password ;


    public Keycloak getKeycloakInstance(){
        if (keycloak == null )
        {
            keycloak = KeycloakBuilder
                    .builder()
                    .serverUrl(serverUrl).realm(realm).clientId(clientId)
                    .grantType(grantType).username(username).password(password).build();
        }
        return keycloak;
    }


}
