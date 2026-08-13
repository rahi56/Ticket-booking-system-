package com.rahim.ticketbooking.booking.controller;

import com.rahim.ticketbooking.booking.entity.Booking;
import com.rahim.ticketbooking.booking.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/bookings")
@RequiredArgsConstructor
public class BookingController {

    private final BookingService service;

    @PostMapping
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public Booking create(@RequestBody Booking booking, Authentication authentication) {
        return service.createForUser(booking, authentication.getName());
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public List<Booking> getAll() {
        return service.getAll();
    }

    @GetMapping("/my-bookings")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public List<Booking> getMyBookings(Authentication authentication) {
        return service.getByUserEmail(authentication.getName());
    }

    @GetMapping("/trip/{tripId}/booked-seats")
    public List<Integer> getBookedSeatsForTrip(@PathVariable Long tripId) {
        return service.getBookedSeatsForTrip(tripId);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public Booking getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public Booking update(
            @PathVariable Long id,
            @RequestBody Booking booking
    ) {
        return service.update(id, booking);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public String delete(@PathVariable Long id) {
        return service.delete(id);
    }
}