package com.rahim.ticketbooking.booking.entity;

import com.rahim.ticketbooking.trip.entity.Trip;
import com.rahim.ticketbooking.user.entity.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "trip_id")
    private Trip trip;

    @NotNull(message = "Seat number is required")
    private Integer seatNumber;

    private Double totalFare;

    private String status;
}