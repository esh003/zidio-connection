package com.zidio_connection.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zidio_connection.dto.JobPostDTO;
import com.zidio_connection.entity.JobPost;
import com.zidio_connection.enums.JobType;
import com.zidio_connection.repository.JobPostRepository;

@Service
public class JobPostService {

    @Autowired
    private JobPostRepository jobPostRepo;

    public JobPost postJob(Long recruiterId, JobPostDTO dto) {
        JobPost job = JobPost.builder()
                .recruiterId(recruiterId)
                .jobTitle(dto.getJobTitle())
                .companyName(dto.getCompanyName())
                .location(dto.getLocation())
                .jobType(dto.getJobType())
                .description(dto.getDescription())
                .skillsRequired(dto.getSkillsRequired())
                .salaryRange(dto.getSalaryRange())
                .build();
        return jobPostRepo.save(job);
    }

    public List<JobPost> getAllJobs() {
        return jobPostRepo.findAll();
    }

    public List<JobPost> getJobsByRecruiter(Long recruiterId) {
        return jobPostRepo.findByRecruiterId(recruiterId);
    }

    public List<JobPost> searchByJobTitle(String jobTitle) {
        return jobPostRepo.findByJobTitleContainingIgnoreCase(jobTitle);
    }

    public List<JobPost> searchByCompany(String companyName) {
        return jobPostRepo.findByCompanyNameContainingIgnoreCase(companyName);
    }

    public List<JobPost> searchByJobType(JobType jobType) {
        return jobPostRepo.findByJobType(jobType);
    }

    public JobPost getJobById(Long id) {
        return jobPostRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Job not found"));
    }
}
