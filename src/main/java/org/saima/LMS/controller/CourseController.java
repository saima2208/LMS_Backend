package org.saima.LMS.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.saima.LMS.dto.CourseDTO;
import org.saima.LMS.dto.UserResponse;
import org.saima.LMS.handler.EntityNotFoundException;
import org.saima.LMS.model.Course;
import org.saima.LMS.model.User;
import org.saima.LMS.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/courses")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @PostMapping
    public ResponseEntity<Course> createCourse(@Validated @RequestBody CourseDTO courseDTO) {
        Course course = courseService.createCourse(courseDTO);
        return ResponseEntity.ok(course);
    }

    @GetMapping
    public ResponseEntity<List<Course>> getAllCourses() {
        List<Course> courses = courseService.getAllCourses();
        return ResponseEntity.ok(courses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Course> getCourseById(@PathVariable Long id) {
        return courseService.getCourseById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    
    @GetMapping("/users/{courseId}")
    public ResponseEntity<List<UserResponse>> getUsersByCourseId(@PathVariable Long courseId) {
    	 List<User> users = courseService.getUsersByCourseId(courseId);
         List<UserResponse> userResponses = users.stream()
                 .map(this::convertToDTO)
                 .collect(Collectors.toList());
         return ResponseEntity.ok(userResponses);
    }
    
    private UserResponse convertToDTO(User user) {
        UserResponse dto = new UserResponse();
        dto.setId(user.getId());
        dto.setEmail(user.getEmail());
        dto.setRole(user.getRole());
        dto.setName(user.getName());
        dto.setFatherName(user.getFatherName());
        dto.setMotherName(user.getMotherName());
        dto.setPhone(user.getPhone());
        dto.setAddress(user.getAddress());
        dto.setAvatarUrl(user.getAvatarUrl());
        dto.setBio(user.getBio());
        return dto;
    }
    
    @GetMapping("/teacher/{teacherId}")
    public ResponseEntity<List<Course>> getCoursesByTeacher(@PathVariable Long teacherId) {
        // Mock retrieval of teacher - in a real app, fetch the teacher from a service or repo
        User teacher = new User();
        teacher.setId(teacherId);

        List<Course> courses = courseService.getCoursesByTeacher(teacher);
        if (courses.isEmpty()) {
            return ResponseEntity.noContent().build(); // 204 No Content
        }
        return ResponseEntity.ok(courses); // 200 OK with course list
    }
    
    
    
    @PutMapping("/{id}")
    public ResponseEntity<Course> updateCourse(@PathVariable Long id, @Validated @RequestBody CourseDTO courseDTO) {
        try {
            Course updatedCourse = courseService.updateCourse(id, courseDTO);
            return ResponseEntity.ok(updatedCourse);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build(); // If the course with the given ID doesn't exist
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null); // For other unexpected errors
        }
    }
    
    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCourse(@PathVariable Long id) {
    	courseService.deleteCourse(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

 
    
    
