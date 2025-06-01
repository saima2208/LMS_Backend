package org.saima.LMS.service;

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

	public Course createCourse(CourseDTO courseDTO) {
		// Fetch Teacher
		User teacher = userRepository.findByIdAndRole(courseDTO.getTeacherId(), Role.TEACHER)
				.orElseThrow(() -> new IllegalArgumentException("Teacher not found"));

		// Fetch Lessons
//        List<Lesson> lessons = courseDTO.getLessonIds() != null
//                ? courseDTO.getLessonIds().stream()
//                .map(id -> lessonRepository.findById(id)
//                        .orElseThrow(() -> new IllegalArgumentException("Lesson not found: " + id)))
//                .collect(Collectors.toList())
//                : null;

		// Create and Save Course
		Course course = new Course(teacher,
//                lessons,
				courseDTO.getCourseName(), courseDTO.getPrice(), courseDTO.getStartDate(), courseDTO.getDuration(),
				courseDTO.getDescription(),courseDTO.getImage());

		return courseRepository.save(course);
	}

	public List<Course> getAllCourses() {
		return courseRepository.findAll();
	}

	public Optional<Course> getCourseById(Long id) {
		return courseRepository.findById(id);
	}
	
	  public List<Course> getCoursesByTeacher(User teacher) {
	        return courseRepository.findCourseByTeacher(teacher);
	    }
	
	public Optional<Course> getCourseByName(String CourseName) {
		return courseRepository.findByCourseName("");
	}


	public Course updateCourse(Long id, CourseDTO courseDTO) {
		Course existingCourse = courseRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("Course not found with ID: " + id));

		// Update course details
		existingCourse.setCourseName(courseDTO.getCourseName());
		existingCourse.setPrice(courseDTO.getPrice());
		existingCourse.setStartDate(courseDTO.getStartDate());
		existingCourse.setDuration(courseDTO.getDuration());
		existingCourse.setDescription(courseDTO.getDescription());
		existingCourse.setImage(courseDTO.getImage());
		// Add other fields as needed

		return courseRepository.save(existingCourse);
	}

	public void deleteCourse(Long id) {
		courseRepository.deleteById(id);
	}
}