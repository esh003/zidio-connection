package com.zidio_connection.dto;

import com.zidio_connection.enums.JobType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class JobPostDTO {
    private String jobTitle;
    private String companyName;
    private String location;
    private JobType jobType;
    private String description;
    private String skillsRequired;
    private String salaryRange;
}
