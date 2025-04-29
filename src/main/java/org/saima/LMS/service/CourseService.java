package org.saima.LMS.service;



import org.saima.LMS.model.Course;
import org.saima.LMS.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;

    // Create a new course
    public Course createCourse(Course course) {
        return courseRepository.save(course);
    }

    // Retrieve all courses
    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    // Retrieve a specific course by ID
    public Optional<Course> getCourseById(Long id) {
        return courseRepository.findById(id);
    }

    // Update an existing course
    public Course updateCourse(Long id, Course courseDetails) {
        return courseRepository.findById(id)
                .map(course -> {
                    course.setName(courseDetails.getName());
                    course.setPrice(courseDetails.getPrice());
                    course.setStartDate(courseDetails.getStartDate());
                    course.setDuration(courseDetails.getDuration());
                    course.setDescription(courseDetails.getDescription());
                    return courseRepository.save(course);
                })
                .orElseThrow(() -> new RuntimeException("Course not found with id: " + id));
    }

    // Delete a course
    public void deleteCourse(Long id) {
        courseRepository.deleteById(id);
    }
}
