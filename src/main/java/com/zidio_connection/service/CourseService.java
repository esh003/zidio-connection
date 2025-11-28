package com.zidio_connection.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.zidio_connection.dto.CourseDTO;
import com.zidio_connection.entity.Course;
import com.zidio_connection.exception.ResourceNotFoundException;
import com.zidio_connection.repository.CourseRepository;

@Service
public class CourseService {

    private final CourseRepository courseRepo;

    public CourseService(CourseRepository courseRepo) {
        this.courseRepo = courseRepo;
    }

    public Course addCourse(Long adminId, CourseDTO dto) {
        Course course = Course.builder()
                .adminId(adminId)
                .title(dto.getTitle())
                .description(dto.getDescription())
                .category(dto.getCategory())
                .duration(dto.getDuration())
                .price(dto.getPrice())
                .active(true)
                .createdAt(LocalDateTime.now())
                .build();
        return courseRepo.save(course);
    }

    public List<Course> getAllActiveCourses() {
        return courseRepo.findByActiveTrue();
    }

    public List<Course> getByCategory(String category) {
        return courseRepo.findByCategoryIgnoreCaseAndActiveTrue(category);
    }

    public Course deactivateCourse(Long id) {
        Course course = courseRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Course not found with id: " + id));
        course.setActive(false);
        return courseRepo.save(course);
    }
}
