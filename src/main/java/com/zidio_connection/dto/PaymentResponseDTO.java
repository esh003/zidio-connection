package com.zidio_connection.dto;

import com.zidio_connection.enums.PaymentStatus;
import com.zidio_connection.enums.PaymentType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaymentResponseDTO {

    private Long id;
    private Long userId;
    private Long subscriptionPlanId;
    private String transactionId;
    private Double amount;
    private PaymentStatus status;
    private PaymentType paymentType;
    private LocalDateTime paymentTime;
}
