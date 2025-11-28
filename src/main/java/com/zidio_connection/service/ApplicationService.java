package com.zidio_connection.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zidio_connection.dto.ApplicationDTO;
import com.zidio_connection.entity.Application;
import com.zidio_connection.enums.ApplicationStatus;
import com.zidio_connection.repository.ApplicationRepository;

@Service
public class ApplicationService {

    @Autowired
    private ApplicationRepository applicationRepo;

    public Application apply(ApplicationDTO dto) {
        Application app = Application.builder()
                .jobId(dto.getJobId())
                .jobseekerId(dto.getJobseekerId())
                .recruiterId(dto.getRecruiterId())
                .status(ApplicationStatus.APPLIED)
                .build();
        return applicationRepo.save(app);
    }

    public List<Application> getByJobseeker(Long jobseekerId) {
        return applicationRepo.findByJobseekerId(jobseekerId);
    }

    public List<Application> getByRecruiter(Long recruiterId) {
        return applicationRepo.findByRecruiterId(recruiterId);
    }

    public List<Application> getByJob(Long jobId) {
        return applicationRepo.findByJobId(jobId);
    }

    public Application updateStatus(Long id, ApplicationStatus status) {
        Application app = applicationRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Application not found"));
        app.setStatus(status);
        return applicationRepo.save(app);
    }
}
