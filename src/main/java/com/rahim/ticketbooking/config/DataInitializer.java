package com.rahim.ticketbooking.config;

import com.rahim.ticketbooking.booking.entity.Booking;
import com.rahim.ticketbooking.booking.repository.BookingRepository;
import com.rahim.ticketbooking.bus.entity.Bus;
import com.rahim.ticketbooking.bus.repository.BusRepository;
import com.rahim.ticketbooking.operator.entity.Operator;
import com.rahim.ticketbooking.operator.repository.OperatorRepository;
import com.rahim.ticketbooking.route.entity.Route;
import com.rahim.ticketbooking.route.repository.RouteRepository;
import com.rahim.ticketbooking.trip.entity.Trip;
import com.rahim.ticketbooking.trip.repository.TripRepository;
import com.rahim.ticketbooking.user.entity.User;
import com.rahim.ticketbooking.user.enums.Role;
import com.rahim.ticketbooking.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalTime;

@Component
@RequiredArgsConstructor
@Slf4j
public class DataInitializer {

    private final UserRepository userRepository;
    private final OperatorRepository operatorRepository;
    private final BusRepository busRepository;
    private final RouteRepository routeRepository;
    private final TripRepository tripRepository;
    private final BookingRepository bookingRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @EventListener(ApplicationReadyEvent.class)
    @Transactional
    public void initDemoData() {
        try {
            if (userRepository.count() > 0) {
                log.info("Database already contains data. Skipping initial seeding.");
                return;
            }
        } catch (Exception e) {
            log.warn("Could not check user count, proceeding to seed data: {}", e.getMessage());
        }

        log.info("Seeding initial demo data for Ticket Booking System...");

        // 1. Users
        User admin = User.builder()
                .firstName("System")
                .lastName("Admin")
                .email("admin@ticket.com")
                .password(passwordEncoder.encode("Admin123!"))
                .phone("+8801700000000")
                .role(Role.ADMIN)
                .build();
        userRepository.save(admin);

        User customer = User.builder()
                .firstName("Rahim")
                .lastName("Uddin")
                .email("user@ticket.com")
                .password(passwordEncoder.encode("User123!"))
                .phone("+8801711223344")
                .role(Role.USER)
                .build();
        userRepository.save(customer);

        // 2. Operators
        Operator greenLine = Operator.builder()
                .name("Green Line Paribahan")
                .email("contact@greenlinebd.com")
                .phone("+8801730000001")
                .address("Rajarbagh, Dhaka")
                .licenseNumber("OP-GL-9988")
                .active(true)
                .build();
        operatorRepository.save(greenLine);

        Operator shohagh = Operator.builder()
                .name("Shohagh Paribahan")
                .email("info@shohagh.com")
                .phone("+8801730000002")
                .address("Malibagh, Dhaka")
                .licenseNumber("OP-SH-8877")
                .active(true)
                .build();
        operatorRepository.save(shohagh);

        Operator hanif = Operator.builder()
                .name("Hanif Enterprise")
                .email("support@hanifenterprise.com")
                .phone("+8801730000003")
                .address("Gabtoli, Dhaka")
                .licenseNumber("OP-HN-7766")
                .active(true)
                .build();
        operatorRepository.save(hanif);

        // 3. Routes
        Route r1 = Route.builder()
                .source("Dhaka")
                .destination("Chittagong")
                .distance(245.0)
                .estimatedDuration(6)
                .build();
        routeRepository.save(r1);

        Route r2 = Route.builder()
                .source("Dhaka")
                .destination("Sylhet")
                .distance(240.0)
                .estimatedDuration(5)
                .build();
        routeRepository.save(r2);

        Route r3 = Route.builder()
                .source("Dhaka")
                .destination("Cox's Bazar")
                .distance(390.0)
                .estimatedDuration(9)
                .build();
        routeRepository.save(r3);

        Route r4 = Route.builder()
                .source("Chittagong")
                .destination("Cox's Bazar")
                .distance(150.0)
                .estimatedDuration(4)
                .build();
        routeRepository.save(r4);

        // 4. Buses
        Bus bus1 = Bus.builder()
                .busNumber("GL-101-AC")
                .registrationNumber("DHAKA-METRO-BA-11-2001")
                .busType("AC Scania Multi-Axle")
                .totalSeats(40)
                .operator(greenLine)
                .build();
        busRepository.save(bus1);

        Bus bus2 = Bus.builder()
                .busNumber("SH-202-AC")
                .registrationNumber("DHAKA-METRO-BA-11-3002")
                .busType("AC Volvo B11R")
                .totalSeats(36)
                .operator(shohagh)
                .build();
        busRepository.save(bus2);

        Bus bus3 = Bus.builder()
                .busNumber("HN-303-DE")
                .registrationNumber("DHAKA-METRO-BA-11-4003")
                .busType("Hyundai Business Class")
                .totalSeats(40)
                .operator(hanif)
                .build();
        busRepository.save(bus3);

        // 5. Trips
        LocalDate today = LocalDate.now();

        Trip t1 = Trip.builder()
                .bus(bus1)
                .route(r1)
                .journeyDate(today)
                .departureTime(LocalTime.of(7, 30))
                .arrivalTime(LocalTime.of(13, 30))
                .fare(1200.00)
                .availableSeats(37)
                .build();
        tripRepository.save(t1);

        Trip t2 = Trip.builder()
                .bus(bus2)
                .route(r1)
                .journeyDate(today)
                .departureTime(LocalTime.of(10, 0))
                .arrivalTime(LocalTime.of(16, 0))
                .fare(1100.00)
                .availableSeats(35)
                .build();
        tripRepository.save(t2);

        Trip t3 = Trip.builder()
                .bus(bus3)
                .route(r3)
                .journeyDate(today.plusDays(1))
                .departureTime(LocalTime.of(22, 30))
                .arrivalTime(LocalTime.of(7, 30))
                .fare(1500.00)
                .availableSeats(38)
                .build();
        tripRepository.save(t3);

        Trip t4 = Trip.builder()
                .bus(bus1)
                .route(r2)
                .journeyDate(today.plusDays(1))
                .departureTime(LocalTime.of(8, 0))
                .arrivalTime(LocalTime.of(13, 30))
                .fare(950.00)
                .availableSeats(40)
                .build();
        tripRepository.save(t4);

        // 6. Sample Initial Bookings
        Booking b1 = Booking.builder()
                .user(customer)
                .trip(t1)
                .seatNumber(5)
                .totalFare(1200.00)
                .status("CONFIRMED")
                .build();
        bookingRepository.save(b1);

        Booking b2 = Booking.builder()
                .user(customer)
                .trip(t1)
                .seatNumber(6)
                .totalFare(1200.00)
                .status("CONFIRMED")
                .build();
        bookingRepository.save(b2);

        Booking b3 = Booking.builder()
                .user(admin)
                .trip(t1)
                .seatNumber(12)
                .totalFare(1200.00)
                .status("CONFIRMED")
                .build();
        bookingRepository.save(b3);

        log.info("Initial demo data seeded successfully!");
        log.info("Admin Credentials: admin@ticket.com / Admin123!");
        log.info("User Credentials:  user@ticket.com  / User123!");
    }
}
