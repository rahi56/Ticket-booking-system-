package com.rahim.ticketbooking.operator.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OperatorResponse {

    private Long id;

    private String name;

    private String email;

    private String phone;

    private String address;

    private String licenseNumber;

    private Boolean active;
}