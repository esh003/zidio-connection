package com.zidio_connection.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.zidio_connection.entity.JobPost;
import com.zidio_connection.enums.JobType;

@Repository
public interface JobPostRepository extends JpaRepository<JobPost, Long> {
    List<JobPost> findByRecruiterId(Long recruiterId);
    List<JobPost> findByJobTitleContainingIgnoreCase(String jobTitle);
    List<JobPost> findByCompanyNameContainingIgnoreCase(String companyName);
    List<JobPost> findByJobType(JobType jobType);
}
