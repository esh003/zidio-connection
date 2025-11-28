package com.zidio_connection.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DashboardDTO {

    private long totalJobseekers;
    private long totalRecruiters;
    private long totalJobPosts;
    private long totalApplications;
    private long totalActiveCourses;
}
