package com.zidio_connection.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.zidio_connection.dto.JobSeekerDTO;
import com.zidio_connection.entity.JobSeeker;
import com.zidio_connection.repository.JobSeekerRepository;

@Service
public class JobSeekerService {

    @Autowired
    private JobSeekerRepository jobseekerRepo;

    public JobSeeker createAndUpdateProfile(Long userId, JobSeekerDTO dto) {
        JobSeeker emp = new JobSeeker();
        emp.setUserId(userId);
        emp.setUserName(dto.getUserName());
        emp.setUserEmail(dto.getUserEmail());
        emp.setCollageName(dto.getCollageName());
        emp.setUniversityName(dto.getUniversityName());
        emp.setCourseName(dto.getCourseName());
        emp.setPassoutYear(dto.getPassoutYear());
        emp.setSkils(dto.getSkils());
        emp.setResumeUrl(dto.getResumeUrl());
        emp.setCertificateImage(dto.getCertificateImage());
        return jobseekerRepo.save(emp);
    }

    public JobSeeker getJobseekersProfile(Long userId) {
        return jobseekerRepo.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("Jobseeker not found"));
    }
}
