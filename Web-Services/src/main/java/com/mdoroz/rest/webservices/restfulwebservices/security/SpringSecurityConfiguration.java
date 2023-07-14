package com.mdoroz.rest.webservices.restfulwebservices.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

import java.security.Security;

@Configuration
public class SpringSecurityConfiguration {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        //making all request with authentication requirement
        http.authorizeHttpRequests(
                auth -> auth.anyRequest().authenticated()
        );

        //if there is no auth then web pop up shows to login
        http.httpBasic(Customizer.withDefaults());

        //POST and PUT will be available to use with authentication
        http.csrf().disable();

        return http.build();
    }
}
