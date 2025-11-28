package com.zidio_connection.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.zidio_connection.dto.PaymentRequestDTO;
import com.zidio_connection.dto.PaymentResponseDTO;
import com.zidio_connection.service.PaymentService;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    // create a payment
    @PostMapping
    public ResponseEntity<PaymentResponseDTO> create(@Validated @RequestBody PaymentRequestDTO dto) {
        return ResponseEntity.ok(paymentService.createPayment(dto));
    }

    // get all payments
    @GetMapping
    public ResponseEntity<List<PaymentResponseDTO>> getAll() {
        return ResponseEntity.ok(paymentService.getAllPayments());
    }

    // get payments of a user
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<PaymentResponseDTO>> getByUser(@PathVariable Long userId) {
        return ResponseEntity.ok(paymentService.getByUser(userId));
    }
}
