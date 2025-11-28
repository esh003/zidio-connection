package com.zidio_connection.controller;

import java.io.File;
import java.io.IOException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.zidio_connection.dto.EmailRequestDTO;
import com.zidio_connection.service.EmailService;

import jakarta.mail.MessagingException;

@RestController
@RequestMapping("/api/notification")
public class NotificationController {

    private final EmailService emailService;

    public NotificationController(EmailService emailService) {
        this.emailService = emailService;
    }

    @PostMapping("/send")
    public ResponseEntity<String> send(@RequestBody EmailRequestDTO dto) {
        emailService.sendEmail(dto);
        return ResponseEntity.ok("Email sent");
    }

    @PostMapping("/send-invoice")
    public ResponseEntity<String> sendInvoice(@RequestBody EmailRequestDTO dto, @RequestParam String filePath) throws MessagingException, IOException {
        File file = new File(filePath);
        if (!file.exists()) return ResponseEntity.badRequest().body("File not found: " + filePath);
        emailService.sendEmailWithAttachment(dto, file);
        return ResponseEntity.ok("Invoice sent");
    }
}
