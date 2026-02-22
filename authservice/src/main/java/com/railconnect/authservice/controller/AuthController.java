package com.railconnect.authservice.controller;

import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import com.railconnect.authservice.dto.LoginRequest;
import com.railconnect.authservice.entity.User;
import com.railconnect.authservice.repository.UserRepository;
import com.railconnect.authservice.security.JwtUtil;
import com.railconnect.authservice.service.UserService;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "http://localhost:5173")
public class AuthController {

    @Autowired
    private UserService service;

    @Autowired
    private UserRepository repo;

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private JwtUtil jwtUtil;
    
    // ✅ REGISTER
    @PostMapping("/register")
    public String register(@RequestBody User user) {

        user.setId(null);   // Important
        user.setPassword(encoder.encode(user.getPassword()));
        user.setRole(user.getRole().toUpperCase());
        repo.save(user);

        return "User Registered Successfully";
    }

    @PostMapping("/login")
    public Map<String, String> login(@RequestBody LoginRequest request) {

        User dbUser = repo.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("User Not Found"));

        if (!encoder.matches(request.getPassword(), dbUser.getPassword())) {
            throw new RuntimeException("Invalid Password");
        }

        String token = jwtUtil.generateToken(
                dbUser.getEmail(),
                dbUser.getRole()
        );

        return Map.of(
                "token", token,
                "role", dbUser.getRole()
        );
    }
}
