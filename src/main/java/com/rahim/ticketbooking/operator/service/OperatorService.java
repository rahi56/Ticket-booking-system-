package com.rahim.ticketbooking.operator.service;

import com.rahim.ticketbooking.common.exception.ResourceNotFoundException;
import com.rahim.ticketbooking.operator.dto.OperatorResponse;
import com.rahim.ticketbooking.operator.entity.Operator;
import com.rahim.ticketbooking.operator.repository.OperatorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OperatorService {

    private final OperatorRepository repository;

    public Operator create(Operator operator) {

        if (repository.existsByName(operator.getName())) {
            throw new ResourceNotFoundException("Operator name already exists");
        }

        if (repository.existsByEmail(operator.getEmail())) {
            throw new ResourceNotFoundException("Operator email already exists");
        }

        if (repository.existsByPhone(operator.getPhone())) {
            throw new ResourceNotFoundException("Operator phone already exists");
        }

        return repository.save(operator);
    }

    public List<Operator> getAll() {
        return repository.findAll();
    }

    public OperatorResponse getOperatorById(Long id) {

        Operator operator = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Operator not found"));

        // Manual mapping — avoids MapStruct annotation-processor dependency
        return OperatorResponse.builder()
                .id(operator.getId())
                .name(operator.getName())
                .email(operator.getEmail())
                .phone(operator.getPhone())
                .address(operator.getAddress())
                .licenseNumber(operator.getLicenseNumber())
                .active(operator.getActive())
                .build();
    }

    public Operator update(Long id, Operator updatedOperator) {

        Operator operator = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Operator not found"));

        operator.setName(updatedOperator.getName());
        operator.setEmail(updatedOperator.getEmail());
        operator.setPhone(updatedOperator.getPhone());
        operator.setAddress(updatedOperator.getAddress());
        operator.setLicenseNumber(updatedOperator.getLicenseNumber());
        operator.setActive(updatedOperator.getActive());

        return repository.save(operator);
    }

    public String delete(Long id) {

        Operator operator = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Operator not found"));

        repository.delete(operator);

        return "Operator deleted successfully";
    }
}