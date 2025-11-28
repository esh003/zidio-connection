package com.zidio_connection.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.zidio_connection.dto.PaymentRequestDTO;
import com.zidio_connection.dto.PaymentResponseDTO;
import com.zidio_connection.entity.Payment;
import com.zidio_connection.enums.PaymentStatus;
import com.zidio_connection.repository.PaymentRepository;

@Service
public class PaymentService {

    private final PaymentRepository paymentRepo;

    public PaymentService(PaymentRepository paymentRepo) {
        this.paymentRepo = paymentRepo;
    }

    // simulate a successful payment + save record
    public PaymentResponseDTO createPayment(PaymentRequestDTO dto) {
        String txnId = "TXN-" + UUID.randomUUID();

        Payment payment = Payment.builder()
                .userId(dto.getUserId())
                .subscriptionPlanId(dto.getSubscriptionPlanId())
                .transactionId(txnId)
                .amount(dto.getAmount())
                .status(PaymentStatus.SUCCESS)       // assuming success for now
                .paymentType(dto.getPaymentType())
                .paymentTime(LocalDateTime.now())
                .build();

        Payment saved = paymentRepo.save(payment);
        return mapToResponse(saved);
    }

    public List<PaymentResponseDTO> getAllPayments() {
        return paymentRepo.findAll()
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    public List<PaymentResponseDTO> getByUser(Long userId) {
        return paymentRepo.findByUserId(userId)
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    private PaymentResponseDTO mapToResponse(Payment p) {
        return PaymentResponseDTO.builder()
                .id(p.getId())
                .userId(p.getUserId())
                .subscriptionPlanId(p.getSubscriptionPlanId())
                .transactionId(p.getTransactionId())
                .amount(p.getAmount())
                .status(p.getStatus())
                .paymentType(p.getPaymentType())
                .paymentTime(p.getPaymentTime())
                .build();
    }
}
