package org.saima.LMS.controller;

import java.util.List;

import org.saima.LMS.dto.AssignmentDTO;
import org.saima.LMS.handler.EntityNotFoundException;
import org.saima.LMS.model.Assignment;
import org.saima.LMS.service.AssignmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/assignments")
@CrossOrigin(origins = "http://localhost:4200")
public class AssignmentController {
	 @Autowired
	    private AssignmentService assignmentService;

	    @PostMapping
	    public ResponseEntity<Assignment> createAssignment(@Validated @RequestBody AssignmentDTO assignmentDTO) {
	    	Assignment assignment = assignmentService.createAssignment(assignmentDTO);
	        return ResponseEntity.ok(assignment);
	    }

	    @GetMapping
	    public ResponseEntity<List<Assignment>> getAllAssignments() {
	        List<Assignment> assignments = assignmentService.getAllAssignments();
	        return ResponseEntity.ok(assignments);
	    }

	    @GetMapping("/{id}")
	    public ResponseEntity<Assignment> getAssignmentById(@PathVariable Long id) {
	        return assignmentService.getAssignmentById(id)
	                .map(ResponseEntity::ok)
	                .orElse(ResponseEntity.notFound().build());
	    }

	    @DeleteMapping("/{id}")
	    public ResponseEntity<Void> deleteAssignment(@PathVariable Long id) {
	    	assignmentService.deleteAssignment(id);
	        return ResponseEntity.noContent().build();
	    }
	    
	    @PutMapping("/{id}")
	    public ResponseEntity<Assignment> updateAssignment(@PathVariable Long id, @Validated @RequestBody AssignmentDTO assignmentDTO) {
	        try {
	        	Assignment updatedAssignment = assignmentService.updateAssignment(id, assignmentDTO);
	            return ResponseEntity.ok(updatedAssignment);
	        } catch (EntityNotFoundException e) {
	            return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // Assignment not found
	        } catch (Exception e) {
	            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build(); // Other errors
	        }
	    }
}
