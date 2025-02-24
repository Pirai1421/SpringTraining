package com.Security.SecurityProject;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http.csrf(customizer -> customizer.disable());
        http.authorizeHttpRequests(request -> request.anyRequest().authenticated());
        //http.formLogin(Customizer.withDefaults());//this is enables to use security for broswer pages even after @enable security is used
        http.httpBasic(Customizer.withDefaults());// this enables to use
        http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));//this create  unique session id  every time u refresh the session
        return http.build();
    }

}
