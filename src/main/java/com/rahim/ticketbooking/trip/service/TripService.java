package com.rahim.ticketbooking.trip.service;

import com.rahim.ticketbooking.bus.entity.Bus;
import com.rahim.ticketbooking.bus.repository.BusRepository;
import com.rahim.ticketbooking.common.exception.ResourceNotFoundException;
import com.rahim.ticketbooking.route.entity.Route;
import com.rahim.ticketbooking.route.repository.RouteRepository;
import com.rahim.ticketbooking.trip.entity.Trip;
import com.rahim.ticketbooking.trip.repository.TripRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TripService {

    private final TripRepository tripRepository;
    private final BusRepository busRepository;
    private final RouteRepository routeRepository;

    public Trip create(Trip trip) {

        Long busId = trip.getBus().getId();
        Long routeId = trip.getRoute().getId();

        Bus bus = busRepository.findById(busId)
                .orElseThrow(() -> new ResourceNotFoundException("Bus not found"));

        Route route = routeRepository.findById(routeId)
                .orElseThrow(() -> new ResourceNotFoundException("Route not found"));

        trip.setBus(bus);
        trip.setRoute(route);

        return tripRepository.save(trip);
    }

    public List<Trip> getAll() {
        return tripRepository.findAll();
    }

    public Trip getById(Long id) {
        return tripRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Trip not found"));
    }

    public List<Trip> search(
            String source,
            String destination,
            LocalDate date
    ) {
        if (source == null || source.isBlank()) source = "";
        if (destination == null || destination.isBlank()) destination = "";

        List<Trip> results = List.of();
        if (date != null) {
            results = tripRepository.findByRoute_SourceContainingIgnoreCaseAndRoute_DestinationContainingIgnoreCaseAndJourneyDate(
                    source,
                    destination,
                    date
            );
        }

        if (results.isEmpty()) {
            results = tripRepository.findByRoute_SourceContainingIgnoreCaseAndRoute_DestinationContainingIgnoreCase(
                    source,
                    destination
            );
        }

        if (results.isEmpty()) {
            results = tripRepository.findAll();
        }

        return results;
    }
    public Trip update(Long id, Trip trip) {

        Trip existingTrip = tripRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Trip not found"));

        Long busId = trip.getBus().getId();
        Long routeId = trip.getRoute().getId();

        Bus bus = busRepository.findById(busId)
                .orElseThrow(() -> new ResourceNotFoundException("Bus not found"));

        Route route = routeRepository.findById(routeId)
                .orElseThrow(() -> new ResourceNotFoundException("Route not found"));

        existingTrip.setBus(bus);
        existingTrip.setRoute(route);
        existingTrip.setJourneyDate(trip.getJourneyDate());
        existingTrip.setDepartureTime(trip.getDepartureTime());
        existingTrip.setArrivalTime(trip.getArrivalTime());
        existingTrip.setFare(trip.getFare());
        existingTrip.setAvailableSeats(trip.getAvailableSeats());

        return tripRepository.save(existingTrip);
    }

    public void delete(Long id) {

        Trip trip = tripRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Trip not found"));

        tripRepository.delete(trip);
    }

}