package com.railconnect.trainservice.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

	    http
	        .csrf(csrf -> csrf.disable())
	        .authorizeHttpRequests(auth -> auth

	            // Allow Swagger
	            .requestMatchers(
	                "/swagger-ui/**",
	                "/v3/api-docs/**",
	                "/swagger-ui.html"
	            ).permitAll()

	            // ADMIN only
	            .requestMatchers(HttpMethod.POST, "/trains/**")
	                .hasAuthority("ADMIN")

	            .requestMatchers(HttpMethod.PUT, "/trains/**")
	                .hasAuthority("ADMIN")

	            .requestMatchers(HttpMethod.DELETE, "/trains/**")
	                .hasAuthority("ADMIN")

	            // View allowed
	            .requestMatchers(HttpMethod.GET, "/trains/**")
	                .hasAnyAuthority("ADMIN", "PASSENGER", "TTE")

	            .anyRequest().authenticated()
	        );

	    return http.build();
	}
}
