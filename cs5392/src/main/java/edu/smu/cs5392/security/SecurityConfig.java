package edu.smu.cs5392.security;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;


@Configuration
public class SecurityConfig {

//    private final CustomUserDetailsService userDetailsService;

//    public SecurityConfig(CustomUserDetailsService userDetailsService) {
 //       this.userDetailsService = userDetailsService;
   // }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable()) // Disable CSRF for simplicity; you can enable it in production
            .cors(cors -> cors.configurationSource(corsConfigurationSource()))

            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/api/users/**").hasRole("ADMIN") // Only ADMIN can access /admin/**
                .requestMatchers("/api/operator/**").hasRole("OPERATOR") // Only OPERATOR can access /operator/**
                .requestMatchers("/api/common/**").hasAnyRole(
                    "ADMIN", "OPERATOR")
                .anyRequest().authenticated()) // All other requests require authentication
            .httpBasic( httpBasic -> {})
            .formLogin(form -> form
                .permitAll())
            .logout(logout -> logout
                .permitAll());
    
        return http.build();
    }


    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("http://localhost:8080", "http://localhost:3000"));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(Arrays.asList("Authorization", "Content-Type"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

//    @Bean
 //   public UserDetailsService userDetailsService() {
   //     return userDetailsService; // (1)
 //   }
}
