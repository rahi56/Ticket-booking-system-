package com.rahim.ticketbooking.route.controller;

import com.rahim.ticketbooking.route.entity.Route;
import com.rahim.ticketbooking.route.service.RouteService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/v1/routes")
@RequiredArgsConstructor
public class RouteController {

    private final RouteService service;

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public Route create(@RequestBody Route route) {
        return service.create(route);
    }

    @GetMapping
    public List<Route> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public Route getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public Route update(
            @PathVariable Long id,
            @RequestBody Route route
    ) {
        return service.update(id, route);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public String delete(@PathVariable Long id) {
        return service.delete(id);
    }
}