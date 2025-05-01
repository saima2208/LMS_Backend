package org.saima.LMS.service;

import org.saima.LMS.dto.CourseDTO;
import org.saima.LMS.model.Course;
import org.saima.LMS.model.Lesson;
import org.saima.LMS.model.User;
import org.saima.LMS.repository.CourseRepository;
import org.saima.LMS.repository.LessonRepository;
import org.saima.LMS.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private LessonRepository lessonRepository;

    public Course createCourse(CourseDTO courseDTO) {
        // Fetch Teacher
        User teacher = userRepository.findById(courseDTO.getTeacherId())
                .orElseThrow(() -> new IllegalArgumentException("Teacher not found"));

        // Fetch Lessons
        List<Lesson> lessons = courseDTO.getLessonIds() != null
                ? courseDTO.getLessonIds().stream()
                .map(id -> lessonRepository.findById(id)
                        .orElseThrow(() -> new IllegalArgumentException("Lesson not found: " + id)))
                .collect(Collectors.toList())
                : null;

        // Create and Save Course
        Course course = new Course(
                teacher,
                lessons,
                courseDTO.getName(),
                courseDTO.getPrice(),
                courseDTO.getDuration(),
                courseDTO.getStartDate(),
                courseDTO.getDescription()
        );

        return courseRepository.save(course);
    }

    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    public Optional<Course> getCourseById(Long id) {
        return courseRepository.findById(id);
    }
}
