package com.learning.app.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@Profile("test")
public class NoSecurityConfig {

    // For removing all the security to all routes for test without security.
    @Bean("testwebSecurityCustomizer")
    WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring().requestMatchers("/**");
    }

    // To disable the csrf and cors to working fine with input
    @Bean
    SecurityFilterChain noSecurity(HttpSecurity http) throws Exception {
        http.cors(cors -> cors.disable());
        http.csrf(csrf -> csrf.disable());
        http.headers(customizer -> customizer.frameOptions(o -> o.disable()));
        return http.build();
    }

}
