package com.zidio_connection.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class JobSeekerDTO {
    private String userName;
    private String userEmail;
    private String collageName;
    private String universityName;
    private String courseName;
    private String passoutYear;
    private String skils;
    private String resumeUrl;
    private String certificateImage;
}
