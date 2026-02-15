package com.railconnect.trainservice.config;

import com.railconnect.trainservice.filter.JwtAuthenticationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import java.util.List;
@Configuration
@EnableWebSecurity // Ensure this import is fixed
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtFilter;

    public SecurityConfig(JwtAuthenticationFilter jwtFilter) {
        this.jwtFilter = jwtFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .authorizeHttpRequests(auth -> auth
                // 1. PUBLIC: Anyone can view trains or search
                .requestMatchers(HttpMethod.GET, "/api/trains/all").permitAll()
                .requestMatchers(HttpMethod.GET, "/api/trains/search").permitAll()

                // 2. ADMIN ONLY: Must have ROLE_ADMIN to change data
                .requestMatchers("/api/trains/add/").hasAuthority("ROLE_ADMIN")
                .requestMatchers("/api/trains/update/*").hasAuthority("ROLE_ADMIN")
                .requestMatchers("/api/trains/delete/*").hasAuthority("ROLE_ADMIN")

                // 3. SECURE: All other requests need a login
                .anyRequest().authenticated()
            )
            .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
