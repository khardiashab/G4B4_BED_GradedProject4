package com.learning.app.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    private final UserDetailsService userDetailsService;

    @Bean(name = "passwordEncoder")
    PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    AuthenticationProvider auth() {
        DaoAuthenticationProvider dao = new DaoAuthenticationProvider();
        dao.setPasswordEncoder(encoder());
        dao.setUserDetailsService(userDetailsService);
        return dao;
    }

    @Bean
    SecurityFilterChain security(HttpSecurity http) throws Exception {
        // Security filter
        http.authorizeHttpRequests(auth -> auth
        .requestMatchers("/h2-console**", "/api/user").permitAll()
        .requestMatchers("/api/")
        );

        // Basic form login
        http.cors(cors -> cors.disable())
                .csrf(csrf -> csrf.disable())
                .formLogin(Customizer.withDefaults());
        return http.build();
    }
}