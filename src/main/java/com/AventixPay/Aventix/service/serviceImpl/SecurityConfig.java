package com.AventixPay.Aventix.service.serviceImpl;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
	    http
	        .csrf(csrf -> csrf.disable())  // Désactive CSRF temporairement
	        .authorizeHttpRequests(auth -> auth
	            .antMatchers("/api/user/**").permitAll()  // Autorise /users/register
	            .antMatchers("api/user").permitAll()  // Autorise tout sous /users/
	            .anyRequest().authenticated()  // Tous les autres endpoints nécessitent une authentification
	        )
	        .formLogin(form -> form
	            .loginPage("/login")  // Page de connexion personnalisée
	            .permitAll()  // Autorise l'accès à la page de login
	        )
	        .logout(logout -> logout
	            .permitAll()  // Autorise l'accès à la déconnexion
	        );

	    return http.build();
	}
	
	
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(List.of("http://localhost:4200")); 
        configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE"));
        configuration.setAllowedHeaders(List.of("*"));
        configuration.setAllowCredentials(true);
        
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
