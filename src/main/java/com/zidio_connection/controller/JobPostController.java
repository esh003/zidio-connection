package com.zidio_connection.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.zidio_connection.dto.JobPostDTO;
import com.zidio_connection.entity.JobPost;
import com.zidio_connection.enums.JobType;
import com.zidio_connection.service.JobPostService;

@RestController
@RequestMapping("/api/jobPosts")
public class JobPostController {

    @Autowired
    private JobPostService jobPostService;

    @PostMapping("/post/{recruiterId}")
    public ResponseEntity<JobPost> postJob(@PathVariable Long recruiterId, @RequestBody JobPostDTO dto) {
        return ResponseEntity.ok(jobPostService.postJob(recruiterId, dto));
    }

    @GetMapping("/all")
    public ResponseEntity<List<JobPost>> getAllJobs() {
        return ResponseEntity.ok(jobPostService.getAllJobs());
    }

    @GetMapping("/recruiter/{recruiterId}")
    public ResponseEntity<List<JobPost>> getJobsByRecruiter(@PathVariable Long recruiterId) {
        return ResponseEntity.ok(jobPostService.getJobsByRecruiter(recruiterId));
    }

    @GetMapping("/search/title/{jobTitle}")
    public ResponseEntity<List<JobPost>> searchByJobTitle(@PathVariable String jobTitle) {
        return ResponseEntity.ok(jobPostService.searchByJobTitle(jobTitle));
    }

    @GetMapping("/search/company/{companyName}")
    public ResponseEntity<List<JobPost>> searchByCompany(@PathVariable String companyName) {
        return ResponseEntity.ok(jobPostService.searchByCompany(companyName));
    }

    @GetMapping("/search/type/{jobType}")
    public ResponseEntity<List<JobPost>> searchByJobType(@PathVariable JobType jobType) {
        return ResponseEntity.ok(jobPostService.searchByJobType(jobType));
    }

    @GetMapping("/{id}")
    public ResponseEntity<JobPost> getJobById(@PathVariable Long id) {
        return ResponseEntity.ok(jobPostService.getJobById(id));
    }
}
