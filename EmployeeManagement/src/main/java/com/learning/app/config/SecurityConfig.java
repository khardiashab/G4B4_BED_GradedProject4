package com.learning.app.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

import com.learning.app.service.impl.UserDetailsServiceImpl;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final UserDetailsServiceImpl userDetailsService;
    private final CustomPasswordEncoder passwordEncoder;

    @Bean
    AuthenticationProvider auth() {
        DaoAuthenticationProvider dao = new DaoAuthenticationProvider();
        dao.setPasswordEncoder(passwordEncoder);
        dao.setUserDetailsService(userDetailsService);
        return dao;
    }

    @Bean
    SecurityFilterChain security(HttpSecurity http) throws Exception {
        // Security filter
        http.authorizeHttpRequests(
            auth -> auth
            .requestMatchers("/h2-console**", "/login**", "/logout**", "/actuator**", "/api/role**", "api/user**").permitAll()
            .requestMatchers(HttpMethod.POST, "/api/roles**", "/api/users**").permitAll()
            .requestMatchers(HttpMethod.POST, "/api/employees").hasRole("ADMIN")
            .requestMatchers("/employees/**").hasAnyRole("ADMIN", "USER")
            // .anyRequest().authenticated()
        );

        // Basic form login
        http.cors(cors -> cors.disable())
                .csrf(csrf -> csrf.disable())
                // .formLogin(Customizer.withDefaults())
                ;
        return http.build();
    }

}