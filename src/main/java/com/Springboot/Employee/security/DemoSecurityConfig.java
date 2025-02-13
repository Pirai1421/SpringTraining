package com.Springboot.Employee.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class DemoSecurityConfig {
    @Bean
    public InMemoryUserDetailsManager userDetailsManager(){
        UserDetails john= User.builder().username("a").password("{noop}Test@1234").roles("EMPLOY").build();
        UserDetails mary= User.builder().username("b").password("{noop}Test@1234").roles("manager").build();
        UserDetails susan= User.builder().username("c").password("{noop}Test@1234").roles("EMPLOY","manager","admin").build();
        return new InMemoryUserDetailsManager(john,mary,susan);

    }
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http.authorizeHttpRequests(configurer->
                configurer
                        .requestMatchers(HttpMethod.GET,"/api/employ").hasRole("EMPLOY")
                        .requestMatchers(HttpMethod.GET,"/api/employ/**").hasRole("EMPLOY")
                        .requestMatchers(HttpMethod.POST,"/api/employ").hasRole("manager")
                        .requestMatchers(HttpMethod.PUT,"/api/employ").hasRole("manager")
                        .requestMatchers(HttpMethod.DELETE,"/api/employ/**").hasRole("admin")
        );
        http.httpBasic(Customizer.withDefaults());
        http.csrf(csrf -> csrf.disable());
        return http.build();
    }
}
