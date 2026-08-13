package com.rahim.ticketbooking.bus.controller;

import com.rahim.ticketbooking.bus.entity.Bus;
import com.rahim.ticketbooking.bus.service.BusService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/buses")
@RequiredArgsConstructor
public class BusController {

    private final BusService service;

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public Bus create(@RequestBody Bus bus) {
        return service.create(bus);
    }

    @GetMapping
    public List<Bus> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public Bus getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public Bus update(
            @PathVariable Long id,
            @RequestBody Bus bus
    ) {
        return service.update(id, bus);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public String delete(@PathVariable Long id) {
        return service.delete(id);
    }
}