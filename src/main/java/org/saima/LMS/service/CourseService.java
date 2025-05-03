package org.saima.LMS.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.saima.LMS.constants.Role;
import org.saima.LMS.dto.CourseDTO;
import org.saima.LMS.handler.EntityNotFoundException;
import org.saima.LMS.model.Course;
import org.saima.LMS.model.User;
import org.saima.LMS.repository.CourseRepository;
import org.saima.LMS.repository.LessonRepository;
import org.saima.LMS.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CourseService {

	@Autowired
	private CourseRepository courseRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private LessonRepository lessonRepository;

//    

	public Course createCourse(CourseDTO courseDTO) {
		// Check if teacher exists with role TEACHER
		User teacher = userRepository.findByUserIdAndRole(courseDTO.getTeacherId(), Role.TEACHER).orElseThrow(
				() -> new IllegalArgumentException("Teacher not found with ID: " + courseDTO.getTeacherId()));

		Course course = new Course();
		course.setName(courseDTO.getName());
		course.setTeacher(teacher);
		course.setPrice(courseDTO.getPrice());
		course.setStartDate(courseDTO.getStartDate() != null ? courseDTO.getStartDate() : LocalDateTime.now());
		course.setDuration(courseDTO.getDuration());
		course.setDescription(courseDTO.getDescription());

		// You can add lessons linking logic here if needed

		return courseRepository.save(course);
	}

	public List<Course> getAllCourses() {
		return courseRepository.findAll();
	}

	public Optional<Course> getCourseById(Long id) {
		return courseRepository.findById(id);
	}

	public Course updateCourse(Long id, CourseDTO courseDTO) {
		Course existingCourse = courseRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("Course not found with ID: " + id));

		// Update course details
		existingCourse.setName(courseDTO.getName());
		existingCourse.setDescription(courseDTO.getDescription());
		existingCourse.setDuration(courseDTO.getDuration());
		// Add other fields as needed

		return courseRepository.save(existingCourse);
	}

	public void deleteCourse(Long id) {
		lessonRepository.deleteById(id);
	}
}
