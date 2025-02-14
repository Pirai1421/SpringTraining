package com.Springboot.Employee.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration(enforceUniqueMethods = false)
public class DemoSecurityConfig {
//    @Bean
//    public InMemoryUserDetailsManager userDetailsManager(){
//        UserDetails john= User.builder().username("a").password("{noop}Test@1234").roles("EMPLOYEE").build();
//        UserDetails mary= User.builder().username("b").password("{noop}Test@1234").roles("manager").build();
//        UserDetails susan= User.builder().username("c").password("{noop}Test@1234").roles("EMPLOYEE","manager","admin").build();
//        return new InMemoryUserDetailsManager(john,mary,susan);
//
//    }
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http.authorizeHttpRequests(configurer->
                configurer
                        .requestMatchers(HttpMethod.GET,"/api/employ").hasRole("EMPLOYEE")
                        .requestMatchers(HttpMethod.GET,"/api/employ/**").hasRole("EMPLOYEE")
                        .requestMatchers(HttpMethod.PUT,"/api/employ").hasRole("manager")
                        .requestMatchers(HttpMethod.DELETE,"/api/employ/**").hasRole("admin")
                        .requestMatchers("/**").permitAll()
        );
        http.httpBasic(Customizer.withDefaults());
        http.csrf(csrf -> csrf.disable());
        return http.build();
    }
    @Bean
    public UserDetailsManager userDetailsManager(DataSource dataSource){
        return new JdbcUserDetailsManager(dataSource);
    }
}
