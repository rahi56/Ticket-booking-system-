package com.rahim.ticketbooking.trip.repository;

import com.rahim.ticketbooking.trip.entity.Trip;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface TripRepository extends JpaRepository<Trip, Long> {

    List<Trip> findByRoute_SourceContainingIgnoreCaseAndRoute_DestinationContainingIgnoreCaseAndJourneyDate(
            String source,
            String destination,
            LocalDate journeyDate
    );

    List<Trip> findByRoute_SourceContainingIgnoreCaseAndRoute_DestinationContainingIgnoreCase(
            String source,
            String destination
    );

    List<Trip> findByRoute_SourceAndRoute_DestinationAndJourneyDate(
            String source,
            String destination,
            LocalDate journeyDate
    );

}