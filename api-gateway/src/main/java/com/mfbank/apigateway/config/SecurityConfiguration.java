package com.mfbank.apigateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsConfigurationSource;

import java.lang.reflect.Array;
import java.util.Arrays;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfiguration {
    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity serverHttpSecurity){
            serverHttpSecurity.csrf(ServerHttpSecurity.CsrfSpec::disable)
                    .authorizeExchange(exchange -> exchange
                            .pathMatchers("/eureka/**")
                            .permitAll()
                            .anyExchange()
                            .authenticated())
                .oauth2ResourceServer(spec -> spec.jwt(Customizer.withDefaults()));
      return   serverHttpSecurity.build();
    }
    @Bean
    public CorsConfigurationSource crosConfigurationSource(){
        return exchange->{
            ServerHttpResponse response =exchange.getResponse();
            HttpHeaders headers =response.getHeaders();
            headers.setAccessControlAllowOrigin("*");
            headers.setAccessControlAllowCredentials(true);
            headers.setAccessControlAllowHeaders(Arrays.asList("Authorization" , "Content-Type"));
            if (exchange.getRequest().getMethod() == HttpMethod.OPTIONS){
                headers.setAccessControlAllowMethods(Arrays.asList(
                        HttpMethod.GET,
                        HttpMethod.DELETE,
                        HttpMethod.POST,
                        HttpMethod.PUT
                ));
                headers.setAccessControlMaxAge(3600L);
                return new CorsConfiguration().applyPermitDefaultValues();
            } return null;
        };

    }
}
