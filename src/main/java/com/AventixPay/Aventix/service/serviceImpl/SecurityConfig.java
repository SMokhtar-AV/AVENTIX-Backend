package com.AventixPay.Aventix.service.serviceImpl;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
        .csrf().disable()  // Disable CSRF protection temporarily
            .authorizeHttpRequests()
                .requestMatchers("/users/register").permitAll()  // Allow /register without authentication
                .requestMatchers("/users/**").permitAll()  // If you want all /users/** endpoints to be public
                .anyRequest().authenticated()  // All other requests need authentication
                .and()
            .formLogin()
                .loginPage("/login")
                .permitAll()
                .and()
            .logout()
                .permitAll();
        
        return http.build();
    }
}
