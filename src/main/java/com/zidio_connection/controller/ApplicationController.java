package com.zidio_connection.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.zidio_connection.dto.ApplicationDTO;
import com.zidio_connection.entity.Application;
import com.zidio_connection.enums.ApplicationStatus;
import com.zidio_connection.service.ApplicationService;

@RestController
@RequestMapping("/api/applications")
public class ApplicationController {

    @Autowired
    private ApplicationService applicationService;

    @PostMapping("/apply")
    public ResponseEntity<Application> apply(@RequestBody ApplicationDTO dto) {
        return ResponseEntity.ok(applicationService.apply(dto));
    }

    @GetMapping("/jobseeker/{jobseekerId}")
    public ResponseEntity<List<Application>> getByJobseeker(@PathVariable Long jobseekerId) {
        return ResponseEntity.ok(applicationService.getByJobseeker(jobseekerId));
    }

    @GetMapping("/recruiter/{recruiterId}")
    public ResponseEntity<List<Application>> getByRecruiter(@PathVariable Long recruiterId) {
        return ResponseEntity.ok(applicationService.getByRecruiter(recruiterId));
    }

    @GetMapping("/job/{jobId}")
    public ResponseEntity<List<Application>> getByJob(@PathVariable Long jobId) {
        return ResponseEntity.ok(applicationService.getByJob(jobId));
    }

    @PutMapping("/status/{id}")
    public ResponseEntity<Application> updateStatus(@PathVariable Long id, @RequestParam ApplicationStatus status) {
        return ResponseEntity.ok(applicationService.updateStatus(id, status));
    }
}
    