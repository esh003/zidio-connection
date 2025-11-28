package com.zidio_connection.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CourseDTO {

    @NotBlank(message = "Course title is required")
    private String title;

    private String description;

    private String category;

    private String duration;

    @NotNull(message = "Price is required")
    private Double price;
}
