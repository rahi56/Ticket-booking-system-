package com.rahim.ticketbooking.route.service;

import com.rahim.ticketbooking.common.exception.ResourceNotFoundException;
import com.rahim.ticketbooking.route.entity.Route;
import com.rahim.ticketbooking.route.repository.RouteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RouteService {

    private final RouteRepository repository;

    public Route create(Route route) {
        return repository.save(route);
    }

    public List<Route> getAll() {
        return repository.findAll();
    }

    public Route getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Route not found"));
    }

    public Route update(Long id, Route updatedRoute) {

        Route route = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Route not found"));

        route.setSource(updatedRoute.getSource());
        route.setDestination(updatedRoute.getDestination());
        route.setDistance(updatedRoute.getDistance());
        route.setEstimatedDuration(updatedRoute.getEstimatedDuration());

        return repository.save(route);
    }

    public String delete(Long id) {

        Route route = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Route not found"));

        repository.delete(route);

        return "Route deleted successfully";
    }
}