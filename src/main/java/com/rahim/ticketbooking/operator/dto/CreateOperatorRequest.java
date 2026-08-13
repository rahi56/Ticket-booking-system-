package com.rahim.ticketbooking.operator.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CreateOperatorRequest {

    @NotBlank(message = "Operator name is required")
    @Size(max = 150)
    private String name;

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email")
    private String email;

    @NotBlank(message = "Phone is required")
    @Size(max = 20)
    private String phone;

    @NotBlank(message = "Address is required")
    private String address;

    @NotBlank(message = "License number is required")
    @Size(max = 50)
    private String licenseNumber;
}