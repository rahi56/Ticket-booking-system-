package com.rahim.ticketbooking.booking.service;

import com.rahim.ticketbooking.booking.entity.Booking;
import com.rahim.ticketbooking.booking.repository.BookingRepository;
import com.rahim.ticketbooking.common.exception.ResourceNotFoundException;
import com.rahim.ticketbooking.trip.entity.Trip;
import com.rahim.ticketbooking.trip.repository.TripRepository;
import com.rahim.ticketbooking.user.entity.User;
import com.rahim.ticketbooking.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookingService {

    private final BookingRepository bookingRepository;
    private final UserRepository userRepository;
    private final TripRepository tripRepository;

    public Booking createForUser(Booking booking, String userEmail) {
        User user;
        if (booking.getUser() != null && booking.getUser().getId() != null) {
            user = userRepository.findById(booking.getUser().getId())
                    .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        } else {
            user = userRepository.findByEmail(userEmail)
                    .orElseThrow(() -> new ResourceNotFoundException("User not found: " + userEmail));
        }

        Long tripId = booking.getTrip().getId();
        Trip trip = tripRepository.findById(tripId)
                .orElseThrow(() -> new ResourceNotFoundException("Trip not found"));

        if (bookingRepository.existsByTripIdAndSeatNumber(trip.getId(), booking.getSeatNumber())) {
            throw new IllegalArgumentException("Seat " + booking.getSeatNumber() + " is already booked for this trip");
        }

        if (trip.getAvailableSeats() <= 0) {
            throw new IllegalStateException("No available seats for this trip");
        }

        // Decrease available seats
        trip.setAvailableSeats(trip.getAvailableSeats() - 1);
        tripRepository.save(trip);

        // Prepare booking
        booking.setUser(user);
        booking.setTrip(trip);
        booking.setTotalFare(trip.getFare());
        booking.setStatus(booking.getStatus() != null ? booking.getStatus() : "CONFIRMED");

        return bookingRepository.save(booking);
    }

    public List<Booking> getByUserEmail(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        return bookingRepository.findByUserId(user.getId());
    }

    public List<Integer> getBookedSeatsForTrip(Long tripId) {
        return bookingRepository.findByTripId(tripId)
                .stream()
                .map(Booking::getSeatNumber)
                .toList();
    }

    public Booking create(Booking booking) {
        Long userId = booking.getUser() != null ? booking.getUser().getId() : null;
        if (userId == null) {
            throw new IllegalArgumentException("User ID is required");
        }
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        return createForUser(booking, user.getEmail());
    }

    public List<Booking> getAll() {
        return bookingRepository.findAll();
    }

    public Booking getById(Long id) {
        return bookingRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Booking not found"));
    }

    public Booking update(Long id, Booking updatedBooking) {

        Booking booking = bookingRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Booking not found"));

        booking.setSeatNumber(updatedBooking.getSeatNumber());
        booking.setStatus(updatedBooking.getStatus());

        return bookingRepository.save(booking);
    }

    public String delete(Long id) {

        Booking booking = bookingRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Booking not found"));

        // Restore seat count
        Trip trip = booking.getTrip();
        if (trip != null) {
            trip.setAvailableSeats(trip.getAvailableSeats() + 1);
            tripRepository.save(trip);
        }

        bookingRepository.delete(booking);

        return "Booking deleted successfully";
    }
}