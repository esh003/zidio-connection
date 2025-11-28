package com.zidio_connection.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.zidio_connection.dto.JobSeekerDTO;
import com.zidio_connection.entity.JobSeeker;
import com.zidio_connection.service.JobSeekerService;

@RestController
@RequestMapping("/api/jobseekers")
public class JobSeekerController {

    @Autowired
    private JobSeekerService jobSeekerService;

    @PostMapping("/profile/{userId}")
    public ResponseEntity<JobSeeker> createAndUpdateProfile(@PathVariable Long userId, @RequestBody JobSeekerDTO dto) {
        return ResponseEntity.ok(jobSeekerService.createAndUpdateProfile(userId, dto));
    }

    @GetMapping("/profile/{userId}")
    public ResponseEntity<JobSeeker> getJobseekersProfile(@PathVariable Long userId) {
        return ResponseEntity.ok(jobSeekerService.getJobseekersProfile(userId));
    }
}
