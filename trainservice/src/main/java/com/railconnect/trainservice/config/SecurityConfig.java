package com.railconnect.trainservice.config;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.Customizer;
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


import java.util.Arrays;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtFilter;

    public SecurityConfig(JwtAuthenticationFilter jwtFilter) {
        this.jwtFilter = jwtFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())
            .cors(Customizer.withDefaults())
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .authorizeHttpRequests(auth -> auth
                // 1. PUBLIC ENDPOINTS
                .requestMatchers(HttpMethod.GET, "/api/trains/all", "/api/trains/search/**").permitAll()
                
                // 2. STATION ENDPOINTS (Add these to fix the 403 error)
                .requestMatchers(HttpMethod.GET, "/api/stations/all").authenticated() // Allow any logged in user
                .requestMatchers("/api/stations/add", "/api/stations/update/**", "/api/stations/delete/**").hasAuthority("ROLE_ADMIN")
                
                // 3. FARE ENDPOINTS (Add these to fix Fare Management 403)
                .requestMatchers(HttpMethod.GET, "/api/fares/all").authenticated()
                .requestMatchers("/api/fares/update/**").hasAuthority("ROLE_ADMIN")

                // 4. TRAIN ADMIN ENDPOINTS
                .requestMatchers("/api/trains/add").hasAuthority("ROLE_ADMIN")
                .requestMatchers(HttpMethod.PUT,"/api/trains/update/**").hasAuthority("ROLE_ADMIN")
                .requestMatchers("/api/trains/delete/**").hasAuthority("ROLE_ADMIN")

                .anyRequest().authenticated()
            )
            .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        // IMPORTANT: Add BOTH ports if you switch between 5173 and 5175
        configuration.setAllowedOrigins(Arrays.asList("http://localhost:5173", "http://localhost:5174", "http://localhost:5175")); 
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(Arrays.asList("Authorization", "Content-Type", "Accept"));
        configuration.setAllowCredentials(true);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}

