package com.rahim.ticketbooking.bus.entity;
import com.rahim.ticketbooking.operator.entity.Operator;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Entity
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Bus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Bus number is rquired")
    private String busNumber;
    @NotBlank(message = "Registration number is required")

    private String registrationNumber;

    @NotBlank(message = "Bus type is required")
    private String busType;
    @NotNull(message = "Total seats is required")
    @Min(value = 1, message = "Total seats must be at least 1")
    private Integer totalSeats;
    @ManyToOne
    @JoinColumn(name="operator_id")
    private Operator operator;

}
