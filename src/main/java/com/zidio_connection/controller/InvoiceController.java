package com.zidio_connection.controller;

import java.io.File;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.zidio_connection.service.InvoiceService;

@RestController
@RequestMapping("/api/invoices")
public class InvoiceController {

    private final InvoiceService invoiceService;

    public InvoiceController(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }

    // 1) Generate invoice PDF on disk and return path
    @PostMapping("/{paymentId}/generate")
    public ResponseEntity<String> generateInvoice(@PathVariable Long paymentId) {
        File file = invoiceService.generateInvoice(paymentId);
        return ResponseEntity.ok("Invoice generated at: " + file.getAbsolutePath());
    }

    // 2) Generate + send invoice PDF via email
    @PostMapping("/{paymentId}/email")
    public ResponseEntity<String> emailInvoice(@PathVariable Long paymentId,
                                               @RequestParam String toEmail) {
        invoiceService.generateAndEmailInvoice(paymentId, toEmail);
        return ResponseEntity.ok("Invoice generated and emailed to " + toEmail);
    }
}
