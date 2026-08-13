package com.rahim.ticketbooking.bus.repository;

import com.rahim.ticketbooking.bus.entity.Bus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BusRepository extends JpaRepository<Bus, Long> {
}