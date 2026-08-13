package com.rahim.ticketbooking.operator.repository;

import com.rahim.ticketbooking.operator.entity.Operator;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OperatorRepository extends JpaRepository<Operator, Long> {
    boolean existsByEmail(String email);

    boolean existsByPhone(String phone);

    boolean existsByName(String name);
}