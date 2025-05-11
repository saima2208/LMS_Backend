package org.saima.LMS.service;

import java.util.List;

import org.saima.LMS.dto.RecordClassDTO;
import org.saima.LMS.model.Course;
import org.saima.LMS.model.RecordClass;
import org.saima.LMS.repository.CourseRepository;
import org.saima.LMS.repository.RecordClassRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RecordClassService {

	 @Autowired
	    private RecordClassRepository recordClassRepository;

	    @Autowired
	    private CourseRepository courseRepository;

	    public RecordClass createRecordClass(RecordClassDTO recordClassDTO) {
	        Course course = courseRepository.findById(recordClassDTO.getCourseId())
	                .orElseThrow(() -> new IllegalArgumentException("Course not found"));

	        RecordClass recordClass = new RecordClass(course, recordClassDTO.getVideoUrl());
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
