package org.saima.LMS.controller;

import java.util.List;

import org.saima.LMS.dto.EnrollmentDTO;
import org.saima.LMS.service.EnrollmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/api/enrollments")
@CrossOrigin(origins = "http://localhost:4200")
public class EnrollmentController {
	 @Autowired
	    private EnrollmentService enrollmentService;

	    @GetMapping
	    public ResponseEntity<List<EnrollmentDTO>> getAllEnrollments() {
	        return ResponseEntity.ok(enrollmentService.getAllEnrollments());
	    }

	    @GetMapping("/{id}")
	    public ResponseEntity<EnrollmentDTO> getEnrollmentById(@PathVariable Long id) {
	        return ResponseEntity.ok(enrollmentService.getEnrollmentById(id));
	    }

//	    @PostMapping
//	    public ResponseEntity<EnrollmentDTO> createEnrollment(@RequestBody EnrollmentDTO dto) {
//	        return ResponseEntity.ok(enrollmentService.createEnrollment(dto));
//	    }

	    @PutMapping("/{id}")
	    public ResponseEntity<EnrollmentDTO> updateEnrollment(@PathVariable Long id, @RequestBody EnrollmentDTO dto) {
	        return ResponseEntity.ok(enrollmentService.updateEnrollment(id, dto));
	    }

	    @DeleteMapping("/{id}")
	    public ResponseEntity<Void> deleteEnrollment(@PathVariable Long id) {
	        enrollmentService.deleteEnrollment(id);
	        return ResponseEntity.noContent().build();
	    }
}
