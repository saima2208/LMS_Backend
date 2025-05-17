package org.saima.LMS.service;

import java.util.List;

import org.saima.LMS.dto.RecordClassDTO;
import org.saima.LMS.model.Course;
import org.saima.LMS.model.Lesson;
import org.saima.LMS.model.RecordClass;
import org.saima.LMS.repository.CourseRepository;
import org.saima.LMS.repository.LessonRepository;
import org.saima.LMS.repository.RecordClassRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RecordClassService {

	 @Autowired
	    private RecordClassRepository recordClassRepository;

	    @Autowired
	    private CourseRepository courseRepository;
	    
	    @Autowired
	    private LessonRepository lessonRepository;

	    public RecordClass createRecordClass(RecordClassDTO recordClassDTO) {
	        Course course = courseRepository.findByCourseName(recordClassDTO.getCourseName())
	                .orElseThrow(() -> new IllegalArgumentException("Course not found"));
	        Lesson lesson = lessonRepository.findById(recordClassDTO.getLessonId())
	                .orElseThrow(() -> new IllegalArgumentException("Lesson not found"));

	        RecordClass recordClass = new RecordClass(course,lesson, recordClassDTO.getVideoUrl());
	        return recordClassRepository.save(recordClass);
	    }

	    public List<RecordClass> getAllRecordClasses() {
	        return recordClassRepository.findAll();
	    }

	    public RecordClass getRecordClassById(Long id) {
	        return recordClassRepository.findById(id)
	                .orElseThrow(() -> new IllegalArgumentException("RecordClass not found"));
	    }

	    public void deleteRecordClass(Long id) {
	        recordClassRepository.deleteById(id);
	    }
}
