package com.aplication.helloevents_app.controller;

import com.aplication.helloevents_app.entity.User;
import com.aplication.helloevents_app.service.AuthService;
import com.aplication.helloevents_app.security.JwtUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @Autowired
    private JwtUtil jwtUtil;

    public static class AuthRequest {
        public String email;
        public String password;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user) {
        User createdUser = authService.registerUser(user);
        return ResponseEntity.ok(createdUser);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest authRequest) {
        boolean authenticated = authService.authenticate(authRequest.email, authRequest.password);
        if (!authenticated) {
            return ResponseEntity.status(401).body("Invalid email or password");
        }
        String token = jwtUtil.generateToken(authRequest.email);
        return ResponseEntity.ok(new AuthResponse(token));
    }

    public static class AuthResponse {
        public String token;
        public AuthResponse(String token) {
            this.token = token;
        }
    }
}
