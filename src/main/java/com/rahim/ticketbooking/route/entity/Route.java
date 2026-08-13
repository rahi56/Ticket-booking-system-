package com.rahim.ticketbooking.route.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Route {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Source is required")
    private String source;
    @NotBlank(message = "Destination is required")
    private String destination;
    @NotNull(message = "Distance is required")
    private Double distance;
    @NotNull(message = "Estimated duration is required")
    private Integer estimatedDuration;
}