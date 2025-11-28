package com.zidio_connection.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.zidio_connection.dto.CourseDTO;
import com.zidio_connection.entity.Course;
import com.zidio_connection.service.CourseService;

@RestController
@RequestMapping("/api/courses")
public class CourseController {

    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    // Add a new course (adminId path variable for future use)
    @PostMapping("/add/{adminId}")
    public ResponseEntity<Course> addCourse(@PathVariable Long adminId,
                                            @Validated @RequestBody CourseDTO dto) {
        return ResponseEntity.ok(courseService.addCourse(adminId, dto));
    }

    // Get all active courses
    @GetMapping
    public ResponseEntity<List<Course>> getAllActiveCourses() {
        return ResponseEntity.ok(courseService.getAllActiveCourses());
    }

    // Filter by category
    @GetMapping("/category/{category}")
    public ResponseEntity<List<Course>> getByCategory(@PathVariable String category) {
        return ResponseEntity.ok(courseService.getByCategory(category));
    }

    // Deactivate a course
    @PutMapping("/deactivate/{id}")
    public ResponseEntity<Course> deactivateCourse(@PathVariable Long id) {
        return ResponseEntity.ok(courseService.deactivateCourse(id));
    }
}
