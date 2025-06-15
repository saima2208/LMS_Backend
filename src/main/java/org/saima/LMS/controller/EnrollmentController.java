package org.saima.LMS.controller;

import java.util.List;

import org.saima.LMS.dto.EnrollmentDTO;
import org.saima.LMS.service.EnrollmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/enrollments")
public class EnrollmentController {

	@Autowired
	private EnrollmentService enrollmentService;

	@PostMapping
	public ResponseEntity<EnrollmentDTO> createEnrollment(@Valid @RequestBody EnrollmentDTO enrollmentDTO) {
		EnrollmentDTO createdEnrollment = enrollmentService.createEnrollment(enrollmentDTO);
		return ResponseEntity.status(HttpStatus.CREATED).body(createdEnrollment);
	}

	@GetMapping("/{id}")
	public ResponseEntity<EnrollmentDTO> getEnrollmentById(@PathVariable Long id) {
		return ResponseEntity.ok(enrollmentService.getEnrollmentById(id));
	}

	@GetMapping
	public ResponseEntity<List<EnrollmentDTO>> getAllEnrollments() {
		return ResponseEntity.ok(enrollmentService.getAllEnrollments());
	}

	@GetMapping("/course/{courseName}")
	public ResponseEntity<List<EnrollmentDTO>> getEnrollmentsByCourseName(@PathVariable String courseName) {
		List<EnrollmentDTO> enrollments = enrollmentService.getEnrollmentsByCourseName(courseName);
		return enrollments.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(enrollments);
	}

	@PutMapping("/updateStatus")
	public ResponseEntity<EnrollmentDTO> updateEnrollmentStatus(@RequestParam Long id, @RequestParam String status) {
		EnrollmentDTO updatedEnrollment = enrollmentService.updateStatus(id, status);
		return ResponseEntity.ok(updatedEnrollment);
	}

	@PutMapping("/{id}")
	public ResponseEntity<EnrollmentDTO> updateEnrollment(@PathVariable Long id,
			@Valid @RequestBody EnrollmentDTO enrollmentDTO) {
		return ResponseEntity.ok(enrollmentService.updateEnrollment(id, enrollmentDTO));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteEnrollment(@PathVariable Long id) {
		enrollmentService.deleteEnrollment(id);
		return ResponseEntity.noContent().build();
	}
}
