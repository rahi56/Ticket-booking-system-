package com.rahim.ticketbooking.bus.service;

import com.rahim.ticketbooking.bus.entity.Bus;
import com.rahim.ticketbooking.bus.repository.BusRepository;
import com.rahim.ticketbooking.common.exception.ResourceNotFoundException;
import com.rahim.ticketbooking.operator.entity.Operator;
import com.rahim.ticketbooking.operator.repository.OperatorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BusService {

    private final BusRepository busRepository;
    private final OperatorRepository operatorRepository;

    public Bus create(Bus bus) {

        Long operatorId = bus.getOperator().getId();

        Operator operator = operatorRepository.findById(operatorId)
                .orElseThrow(() -> new ResourceNotFoundException("Operator not found"));

        bus.setOperator(operator);

        return busRepository.save(bus);
    }

    public List<Bus> getAll() {
        return busRepository.findAll();
    }

    public Bus getById(Long id) {
        return busRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Bus not found"));
    }

    public Bus update(Long id, Bus updatedBus) {

        Bus bus = busRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Bus not found"));

        bus.setBusNumber(updatedBus.getBusNumber());
        bus.setRegistrationNumber(updatedBus.getRegistrationNumber());
        bus.setBusType(updatedBus.getBusType());
        bus.setTotalSeats(updatedBus.getTotalSeats());

        Long operatorId = updatedBus.getOperator().getId();

        Operator operator = operatorRepository.findById(operatorId)
                .orElseThrow(() -> new ResourceNotFoundException("Operator not found"));

        bus.setOperator(operator);

        return busRepository.save(bus);
    }

    public String delete(Long id) {

        Bus bus = busRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Bus not found"));

        busRepository.delete(bus);

        return "Bus deleted successfully";
    }

}