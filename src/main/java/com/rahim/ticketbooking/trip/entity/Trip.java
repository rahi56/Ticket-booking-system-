package com.rahim.ticketbooking.trip.entity;

import com.rahim.ticketbooking.bus.entity.Bus;
import com.rahim.ticketbooking.route.entity.Route;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Trip {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "bus_id")
    private Bus bus;

    @ManyToOne
    @JoinColumn(name = "route_id")
    private Route route;
    @NotNull(message = "Journey date is required")
    private LocalDate journeyDate;

    @NotNull(message = "Departure time is required")
    private LocalTime departureTime;
    @NotNull(message = "Arrival time is required")
    private LocalTime arrivalTime;
    @NotNull(message = "Fare is required")
    private Double fare;

    @NotNull(message = "Available seats is required")
    private Integer availableSeats;
}