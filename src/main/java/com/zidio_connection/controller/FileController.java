package com.zidio_connection.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.zidio_connection.service.FileStorageService;

@RestController
@RequestMapping("/api/files")
public class FileController {

    private final FileStorageService storageService;

    public FileController(FileStorageService storageService) {
        this.storageService = storageService;
    }

    // Example: upload resume for jobseeker
    @PostMapping("/upload/resume")
    public ResponseEntity<String> uploadResume(@RequestParam("file") MultipartFile file, @RequestParam(value = "userId", required = false) String userId) {
        String folder = "zidio/resumes";
        if (userId != null) folder = folder + "/" + userId;
        String url = storageService.uploadFile(file, folder);
        return ResponseEntity.ok(url);
    }

    // Example: upload certificate/image
    @PostMapping("/upload/certificate")
    public ResponseEntity<String> uploadCertificate(@RequestParam("file") MultipartFile file, @RequestParam(value = "userId", required = false) String userId) {
        String folder = "zidio/certificates";
        if (userId != null) folder = folder + "/" + userId;
        String url = storageService.uploadFile(file, folder);
        return ResponseEntity.ok(url);
    }
}
