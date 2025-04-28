package org.saima.LMS.service;

import org.saima.LMS.model.Course;
import org.saima.LMS.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseService {
    private final CourseRepository courseRepository;

    @Autowired
    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    public Optional<Course> getCourseById(Long id) {
        return courseRepository.findById(id);
    }

    public Course createCourse(Course course) {
        return courseRepository.save(course);
    }

//    public Course updateCourse(Long id, Course updatedCourse) {
//        return courseRepository.findById(id)
//                .map(course -> {
//                    course.setName(updatedCourse.getName());
//                    course.setDescription(updatedCourse.getDescription());
//                    course.setLesson(updatedCourse.getLesson());
//                    course.setPrice(updatedCourse.getPrice());
//                    course.setStartDate(updatedCourse.getStartDate());
//                    course.setDuration(updatedCourse.getDuration());
//                    course.setTeacher(updatedCourse.getTeacher());
//                    return courseRepository.save(course);
//                })
//
//
//                .orElseThrow(() -> new ResourceNotFoundException("Course not found"));
//
//    }

    public void deleteCourse(Long id) {
        courseRepository.deleteById(id);
    }


    public Course updateCourse(Long id, Course course) {
        Course courseById = courseRepository.findById(id).orElse(null);

        if (courseById != null) {
            if (course.getName() != null) {
                courseById.setName(course.getName());
            }
            if (course.getLesson() != null) {
                courseById.setLesson(course.getLesson());
            }
            if (course.getPrice() != null) {
                courseById.setPrice(course.getPrice());
            }
            if (course.getStartDate() != null) {
                courseById.setStartDate(course.getStartDate());
            }
            if (course.getDuration() != null) {
                courseById.setDuration(course.getDuration());
            }
            if (course.getTeacher() != null) {
                courseById.setTeacher(course.getTeacher());
            }

            return courseRepository.save(courseById);
        } else {
            return null;
        }

    }
}
