package com.railconnect.trainservice.filter;

import com.railconnect.trainservice.util.JwtUtil; // Check your package name here
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
import java.util.Collections;
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
            String token = authHeader.substring(7).trim(); // trim() removes accidental spaces
            
            try {
                Claims claims = jwtUtil.extractAllClaims(token);
                String username = claims.getSubject();
                
                // Try to get role from "role" or "roles" or "authorities"
                Object roleObj = claims.get("role");
                if (roleObj == null) roleObj = claims.get("roles");
                
                String role = (roleObj != null) ? roleObj.toString() : "";

                // 🔍 DEBUG: CHECK YOUR CONSOLE FOR THESE TWO LINES
                System.out.println("USER: " + username);
                System.out.println("ROLE FROM TOKEN: " + role);

                if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                    // Ensure the role starts with ROLE_ for .hasRole() to work
                    String finalRole = role.startsWith("ROLE_") ? role : "ROLE_" + role;
                    
                    SimpleGrantedAuthority authority = new SimpleGrantedAuthority(finalRole);

                    UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                            username, null, List.of(authority));
                    
                    authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authToken);
                    
                    System.out.println("✅ SecurityContext successfully set for: " + username);
                }
            } catch (Exception e) {
                System.out.println("❌ JWT Auth Failed: " + e.getMessage());
            }
        }
        chain.doFilter(request, response);
    }
}