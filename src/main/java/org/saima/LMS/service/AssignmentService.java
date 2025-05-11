package org.saima.LMS.service;

import java.util.List;
import java.util.Optional;

import org.saima.LMS.dto.AssignmentDTO;
import org.saima.LMS.handler.EntityNotFoundException;
import org.saima.LMS.model.Assignment;
import org.saima.LMS.model.Course;
import org.saima.LMS.repository.AssignmentRepository;
import org.saima.LMS.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class AssignmentService {
	  @Autowired
	    private AssignmentRepository assignmentRepository;

	    @Autowired
	    private CourseRepository courseRepository;

	    public Assignment createAssignment(AssignmentDTO assignmentDTO) {
	        // Fetch the course for the assignment
	        Course course = courseRepository.findById(assignmentDTO.getCourseId())
	                .orElseThrow(() -> new IllegalArgumentException("Course not found"));

	        // Create and save the lesson
	        Assignment assignment = new Assignment(course, assignmentDTO.getTopic(), assignmentDTO.getDueDate());
	        return assignmentRepository.save(assignment);
	    }

	    public List<Assignment> getAllAssignments() {
	        return assignmentRepository.findAll();
	    }

	    public Optional<Assignment> getAssignmentById(Long id) {
	        return assignmentRepository.findById(id);
	    }

	    public void deleteAssignment(Long id) {
	    	assignmentRepository.deleteById(id);
	    }
	    
	    public Assignment updateAssignment(Long id, AssignmentDTO assignmentDTO) {
	    	Assignment existingAssignment = assignmentRepository.findById(id)
	                .orElseThrow(() -> new EntityNotFoundException("Assignment not found with ID: " + id));
	        
	        // Update fields of the assignment
	        existingAssignment.setTopic(assignmentDTO.getTopic());
	        existingAssignment.setDueDate(assignmentDTO.getDueDate());
	       
	       
	        return assignmentRepository.save(existingAssignment);
	    }

}
