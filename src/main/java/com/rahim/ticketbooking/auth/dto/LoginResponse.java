package com.rahim.ticketbooking.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponse {
    private Long id;
    private String token;
    @Builder.Default
    private String tokenType = "Bearer";
    private String email;
    private String firstName;
    private String lastName;
    private String role;

    public LoginResponse(String token) {
        this.token = token;
        this.tokenType = "Bearer";
    }
}
