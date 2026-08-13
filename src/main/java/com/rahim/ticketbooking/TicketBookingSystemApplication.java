package com.rahim.ticketbooking;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan(basePackages = "com.rahim.ticketbooking")
@EnableJpaRepositories(basePackages = "com.rahim.ticketbooking")
public class TicketBookingSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(TicketBookingSystemApplication.class, args);
    }

}
