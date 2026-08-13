package com.rahim.ticketbooking.admin;

import com.rahim.ticketbooking.booking.repository.BookingRepository;
import com.rahim.ticketbooking.bus.repository.BusRepository;
import com.rahim.ticketbooking.operator.repository.OperatorRepository;
import com.rahim.ticketbooking.route.repository.RouteRepository;
import com.rahim.ticketbooking.trip.repository.TripRepository;
import com.rahim.ticketbooking.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/admin")
@RequiredArgsConstructor
public class AdminStatsController {

    private final UserRepository userRepository;
    private final OperatorRepository operatorRepository;
    private final BusRepository busRepository;
    private final RouteRepository routeRepository;
    private final TripRepository tripRepository;
    private final BookingRepository bookingRepository;

    @GetMapping("/stats")
    @PreAuthorize("hasRole('ADMIN')")
    public Map<String, Object> getSystemStats() {
        Map<String, Object> stats = new HashMap<>();

        long totalUsers = userRepository.count();
        long totalOperators = operatorRepository.count();
        long totalBuses = busRepository.count();
        long totalRoutes = routeRepository.count();
        long totalTrips = tripRepository.count();
        long totalBookings = bookingRepository.count();

        // Calculate total revenue from all confirmed bookings
        double totalRevenue = bookingRepository.findAll().stream()
                .filter(b -> "CONFIRMED".equalsIgnoreCase(b.getStatus()))
                .mapToDouble(b -> b.getTotalFare() != null ? b.getTotalFare() : 0.0)
                .sum();

        long activeBookings = bookingRepository.findAll().stream()
                .filter(b -> "CONFIRMED".equalsIgnoreCase(b.getStatus()))
                .count();

        long cancelledBookings = bookingRepository.findAll().stream()
                .filter(b -> "CANCELLED".equalsIgnoreCase(b.getStatus()))
                .count();

        stats.put("totalUsers", totalUsers);
        stats.put("totalOperators", totalOperators);
        stats.put("totalBuses", totalBuses);
        stats.put("totalRoutes", totalRoutes);
        stats.put("totalTrips", totalTrips);
        stats.put("totalBookings", totalBookings);
        stats.put("activeBookings", activeBookings);
        stats.put("cancelledBookings", cancelledBookings);
        stats.put("totalRevenue", Math.round(totalRevenue));

        return stats;
    }
}
