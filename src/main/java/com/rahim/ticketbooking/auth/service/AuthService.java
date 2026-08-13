package com.rahim.ticketbooking.auth.service;

import com.rahim.ticketbooking.auth.dto.LoginRequest;
import com.rahim.ticketbooking.auth.dto.LoginResponse;
import com.rahim.ticketbooking.auth.dto.RegisterRequest;
import com.rahim.ticketbooking.common.exception.ResourceNotFoundException;
import com.rahim.ticketbooking.security.service.JwtService;
import com.rahim.ticketbooking.user.entity.User;
import com.rahim.ticketbooking.user.enums.Role;
import com.rahim.ticketbooking.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public String register(RegisterRequest request) {

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new ResourceNotFoundException("Email already exists");
        }

        Role assignedRole = request.getRole() != null ? request.getRole() : Role.USER;

        User user = User.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .phone(request.getPhone())
                .role(assignedRole)
                .build();

        userRepository.save(user);

        return "User registered successfully";
    }

    public LoginResponse login(LoginRequest request) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        String token = jwtService.generateToken(user.getEmail());

        return LoginResponse.builder()
                .id(user.getId())
                .token(token)
                .tokenType("Bearer")
                .email(user.getEmail())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .role(user.getRole().name())
                .build();
    }
}