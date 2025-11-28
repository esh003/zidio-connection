package com.zidio_connection.entity;

import java.time.LocalDateTime;

import com.zidio_connection.enums.PaymentStatus;
import com.zidio_connection.enums.PaymentType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "payments")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // who paid
    private Long userId;

    // which subscription plan they paid for
    private Long subscriptionPlanId;

    // reference string from payment gateway (or your own)
    @Column(nullable = false, unique = true)
    private String transactionId;

    // total amount paid
    @Column(nullable = false)
    private Double amount;

    // SUCCESS / PENDING / FAILED
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PaymentStatus status;

    // UPI / CREDITCARD / etc
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PaymentType paymentType;

    // when it happened
    private LocalDateTime paymentTime;
}
