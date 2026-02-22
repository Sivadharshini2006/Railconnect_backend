package com.railconnect.trainservice.filter;

import com.railconnect.trainservice.util.JwtUtil; 
import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;

    public JwtAuthenticationFilter(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {

        String authHeader = request.getHeader("Authorization");

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7).trim();
            
            try {
                Claims claims = jwtUtil.extractAllClaims(token);
                String username = claims.getSubject();
                
                // 1. Better Role Extraction (Handles Strings or Lists)
                Object roleObj = claims.get("role");
                if (roleObj == null) roleObj = claims.get("roles");
                
                List<SimpleGrantedAuthority> authorities = new ArrayList<>();

                if (roleObj instanceof List<?>) {
                    // If the token has a list of roles
                    ((List<?>) roleObj).forEach(role -> {
                        String r = role.toString().toUpperCase();
                        authorities.add(new SimpleGrantedAuthority(r.startsWith("ROLE_") ? r : "ROLE_" + r));
                    });
                } else if (roleObj != null) {
                    // If the token has a single string role
                    String r = roleObj.toString().toUpperCase();
                    authorities.add(new SimpleGrantedAuthority(r.startsWith("ROLE_") ? r : "ROLE_" + r));
                }

                // 🔍 DEBUG: Check these in your Eclipse Console
                System.out.println("USER: " + username);
                System.out.println("AUTHORITIES SET: " + authorities);

                if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                    // 2. Use the 3-parameter constructor (Principal, Credentials, Authorities)
                    // This sets the user as 'Authenticated' in Spring Security
                    UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                            username, null, authorities);
                    
                    authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authToken);
                    
                    System.out.println("SecurityContext successfully set for: " + username);
                }
            } catch (Exception e) {
                System.out.println("JWT Auth Failed: " + e.getMessage());
            }
        }
        // Always continue the filter chain
        chain.doFilter(request, response);
    }
}