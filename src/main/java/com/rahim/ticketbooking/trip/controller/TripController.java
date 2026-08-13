package com.rahim.ticketbooking.trip.controller;

import com.rahim.ticketbooking.trip.entity.Trip;
import com.rahim.ticketbooking.trip.service.TripService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/v1/trips")
@RequiredArgsConstructor
public class TripController {

    private final TripService service;

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public Trip create(@RequestBody Trip trip) {
        return service.create(trip);
    }

    @GetMapping
    public List<Trip> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public Trip getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @GetMapping("/search")
    public List<Trip> searchTrips(
            @RequestParam(required = false) String source,
            @RequestParam(required = false) String destination,
            @RequestParam(required = false) LocalDate date
    ) {
        return service.search(source, destination, date);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public Trip update(
            @PathVariable Long id,
            @RequestBody Trip trip
    ) {
        return service.update(id, trip);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public String delete(@PathVariable Long id) {
        service.delete(id);
        return "Trip deleted successfully";
    }
}