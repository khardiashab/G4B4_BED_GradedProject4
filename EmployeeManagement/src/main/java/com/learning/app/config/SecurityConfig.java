package com.learning.app.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
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
    AuthenticationProvider authProvider() {
        DaoAuthenticationProvider dao = new DaoAuthenticationProvider();
        dao.setPasswordEncoder(passwordEncoder);
        dao.setUserDetailsService(userDetailsService);
        return dao;
    }

    // For removing all the security to all routes for test without security.
    @Bean
    WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().requestMatchers("/h2-console/**");
    }

    @Bean
    SecurityFilterChain configure(HttpSecurity http) throws Exception {
        // Security filter
        http.authorizeHttpRequests(
                auth -> auth
                        .requestMatchers("/h2-console**", "/login", "/logout", "/api/roles**", "api/users**")
                        .permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/employees/").hasAnyAuthority("ADMIN", "USER")
                        .requestMatchers(HttpMethod.POST, "/api/employees", "/api/employees/{id}").hasAuthority("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/api/roles", "/api/users").permitAll()
                        .anyRequest().authenticated());

        http.cors(c -> c.disable());
        http.csrf(csrf -> csrf.disable());
        http.httpBasic(Customizer.withDefaults());
        http.formLogin(Customizer.withDefaults());
        http.headers(customizer -> customizer.frameOptions(o -> o.disable()));

        return http.build();
    }

}