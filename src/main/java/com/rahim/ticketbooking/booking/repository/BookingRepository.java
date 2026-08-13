package com.rahim.ticketbooking.booking.repository;

import com.rahim.ticketbooking.booking.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Long> {

    boolean existsByTripIdAndSeatNumber(Long tripId, Integer seatNumber);

    List<Booking> findByTripId(Long tripId);

    List<Booking> findByUserId(Long userId);

}