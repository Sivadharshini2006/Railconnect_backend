package com.railconnect.authservice.security;

import java.security.Key;
import java.util.Date;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {

    private static final String SECRET =
            "mysecretkeymysecretkeymysecretkey123";

    private final Key key =
            Keys.hmacShaKeyFor(SECRET.getBytes());

    public String generateToken(String email, String role) {

        return Jwts.builder()
        .setSubject(email)
        .claim("role", "ROLE_" + role.toUpperCase())
        .setIssuedAt(new Date())
        .setExpiration(new Date(System.currentTimeMillis() + 3600000))
        .signWith(key, SignatureAlgorithm.HS256)
        .compact();
    }
}
