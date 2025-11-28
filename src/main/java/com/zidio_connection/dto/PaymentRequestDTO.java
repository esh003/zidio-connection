package com.zidio_connection.dto;

import com.zidio_connection.enums.PaymentType;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaymentRequestDTO {

    @NotNull
    private Long userId;

    @NotNull
    private Long subscriptionPlanId;

    @NotNull
    private Double amount;

    @NotNull
    private PaymentType paymentType;
}
