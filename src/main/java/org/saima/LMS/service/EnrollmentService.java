package org.saima.LMS.service;

import java.util.List;
import java.util.stream.Collectors;

import org.saima.LMS.constants.Role;
import org.saima.LMS.dto.EnrollmentDTO;
import org.saima.LMS.model.Enrollment;
import org.saima.LMS.model.User;
import org.saima.LMS.repository.CourseRepository;
import org.saima.LMS.repository.EnrollmentRepository;
import org.saima.LMS.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EnrollmentService {
	 @Autowired
	    private EnrollmentRepository enrollmentRepository;
	 
	  @Autowired
	    private CourseRepository courseRepository;

	    @Autowired
	    private UserRepository userRepository;

	    public List<EnrollmentDTO> getAllEnrollments() {
	        return enrollmentRepository.findAll().stream()
	            .map(enrollment -> new EnrollmentDTO(
	                enrollment.getId(),
	                enrollment.getStudentId().getId(),
	                enrollment.getCourse().getId(),
	                enrollment.getEnrollmentDate(),
	                enrollment.getPaymentMethod()))
	            .collect(Collectors.toList());
	    }

	    public EnrollmentDTO getEnrollmentById(Long id) {
	        return enrollmentRepository.findById(id)
	            .map(enrollment -> new EnrollmentDTO(
	                enrollment.getId(),
	                enrollment.getStudentId().getId(),
	                enrollment.getCourse().getId(),
	                enrollment.getEnrollmentDate(),
	                enrollment.getPaymentMethod()))
	            .orElseThrow(() -> new RuntimeException("Enrollment not found"));
	    }

	   

	    public EnrollmentDTO updateEnrollment(Long id, EnrollmentDTO dto) {
	        Enrollment enrollment = enrollmentRepository.findById(id)
	            .orElseThrow(() -> new RuntimeException("Enrollment not found"));

	        enrollment.setEnrollmentDate(dto.getEnrollmentDate());
	        enrollment.setPaymentMethod(dto.getPaymentMethod());

	        enrollment = enrollmentRepository.save(enrollment);
	        return new EnrollmentDTO(
	            enrollment.getId(),
	            enrollment.getStudentId().getId(),
	            enrollment.getCourse().getId(),
	            enrollment.getEnrollmentDate(),
	            enrollment.getPaymentMethod());
	    }

	    public void deleteEnrollment(Long id) {
	        enrollmentRepository.deleteById(id);
	    }
}
