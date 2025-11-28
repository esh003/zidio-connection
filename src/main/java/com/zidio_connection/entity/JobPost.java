package com.zidio_connection.entity;

//import javax.persistence.*;

import com.zidio_connection.enums.JobType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "job_posts")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class JobPost {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long recruiterId;
    private String jobTitle;
    private String companyName;
    private String location;

    @Enumerated(EnumType.STRING)
    private JobType jobType;

    private String description;
    private String skillsRequired;
    private String salaryRange;
}
