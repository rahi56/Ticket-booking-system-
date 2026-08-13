package com.rahim.ticketbooking.operator.controller;

import com.rahim.ticketbooking.operator.entity.Operator;
import com.rahim.ticketbooking.operator.dto.OperatorResponse;
import com.rahim.ticketbooking.operator.service.OperatorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/operators")
@RequiredArgsConstructor
public class OperatorController {

    private final OperatorService service;

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public Operator create(@Valid @RequestBody Operator operator) {
        return service.create(operator);
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    public List<Operator> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    public OperatorResponse getOperatorById(@PathVariable Long id) {
        return service.getOperatorById(id);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public Operator updateOperatorById(
            @PathVariable Long id,
            @RequestBody Operator operator
    ) {
        return service.update(id, operator);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public String deleteOperatorById(@PathVariable Long id) {
        return service.delete(id);
    }
}