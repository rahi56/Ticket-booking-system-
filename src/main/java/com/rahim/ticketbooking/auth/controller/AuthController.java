package com.rahim.ticketbooking.auth.controller;

import com.rahim.ticketbooking.auth.dto.LoginRequest;
import com.rahim.ticketbooking.auth.dto.LoginResponse;
import com.rahim.ticketbooking.auth.dto.RegisterRequest;
import com.rahim.ticketbooking.auth.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @GetMapping("/hello")
    public String hello() {
        return "HELLO";
    }
    @PostMapping("/register")
    public String register(@Valid @RequestBody RegisterRequest request) {
        return authService.register(request);
    }

    @PostMapping("/login")
    public LoginResponse login(@Valid @RequestBody LoginRequest request) {
        return authService.login(request);
    }

    @GetMapping("/me")
    public Object me(Authentication authentication) {
        return authentication.getAuthorities();
    }

}


