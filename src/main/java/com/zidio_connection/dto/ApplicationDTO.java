package com.zidio_connection.dto;

import com.zidio_connection.enums.ApplicationStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ApplicationDTO {
    private Long jobId;
    private Long jobseekerId;
    private Long recruiterId;
    private ApplicationStatus status;
}
