package com.rahim.ticketbooking.route.repository;

import com.rahim.ticketbooking.route.entity.Route;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RouteRepository extends JpaRepository<Route, Long> {
}