package com.zidio_connection.service;

import org.springframework.stereotype.Service;

import com.zidio_connection.dto.DashboardDTO;
import com.zidio_connection.enums.Role;
import com.zidio_connection.repository.ApplicationRepository;
import com.zidio_connection.repository.CourseRepository;
import com.zidio_connection.repository.JobPostRepository;
import com.zidio_connection.repository.UserRepository;

@Service
public class DashboardService {

    private final UserRepository userRepo;
    private final JobPostRepository jobPostRepo;
    private final ApplicationRepository applicationRepo;
    private final CourseRepository courseRepo;

    public DashboardService(UserRepository userRepo,
                            JobPostRepository jobPostRepo,
                            ApplicationRepository applicationRepo,
                            CourseRepository courseRepo) {
        this.userRepo = userRepo;
        this.jobPostRepo = jobPostRepo;
        this.applicationRepo = applicationRepo;
        this.courseRepo = courseRepo;
    }

    public DashboardDTO getSummary() {
        long totalJobseekers = userRepo.countByRole(Role.STUDENT);
        long totalRecruiters = userRepo.countByRole(Role.RECRUITER);
        long totalJobPosts = jobPostRepo.count();
        long totalApplications = applicationRepo.count();
        long totalActiveCourses = courseRepo.findByActiveTrue().size();

        return DashboardDTO.builder()
                .totalJobseekers(totalJobseekers)
                .totalRecruiters(totalRecruiters)
                .totalJobPosts(totalJobPosts)
                .totalApplications(totalApplications)
                .totalActiveCourses(totalActiveCourses)
                .build();
    }
}
