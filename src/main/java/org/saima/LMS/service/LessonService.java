package org.saima.LMS.service;

import org.saima.LMS.model.Lesson;
import org.saima.LMS.dto.LessonDTO;
import org.saima.LMS.handler.EntityNotFoundException;
import org.saima.LMS.model.Course;
import org.saima.LMS.repository.CourseRepository;
import org.saima.LMS.repository.LessonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LessonService {

	  @Autowired
	    private LessonRepository lessonRepository;

	    @Autowired
	    private CourseRepository courseRepository;

	    public Lesson createLesson(LessonDTO lessonDTO) {
	        // Fetch the course for the lesson
	        Course course = courseRepository.findById(lessonDTO.getCourseId())
	                .orElseThrow(() -> new IllegalArgumentException("Course not found"));

	        // Create and save the lesson
	        Lesson lesson = new Lesson(course, lessonDTO.getTopic(), lessonDTO.getDescription());
	        return lessonRepository.save(lesson);
	    }

	    public List<Lesson> getAllLessons() {
	        return lessonRepository.findAll();
	    }

	    public Optional<Lesson> getLessonById(Long id) {
	        return lessonRepository.findById(id);
	    }

	    public void deleteLesson(Long id) {
	        lessonRepository.deleteById(id);
	    }
	    
	    public Lesson updateLesson(Long id, LessonDTO lessonDTO) {
	        Lesson existingLesson = lessonRepository.findById(id)
	                .orElseThrow(() -> new EntityNotFoundException("Lesson not found with ID: " + id));
	        
	        // Update fields of the lesson
	        existingLesson.setTopic(lessonDTO.getTopic());
	        existingLesson.setDescription(lessonDTO.getDescription());
	       
	       
	        return lessonRepository.save(existingLesson);
	    }

}
