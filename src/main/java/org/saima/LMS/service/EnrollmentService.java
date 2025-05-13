package org.saima.LMS.service;

import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

import org.saima.LMS.constants.EnrollmentStatus;
import org.saima.LMS.constants.Role;
import org.saima.LMS.dto.EnrollmentDTO;
import org.saima.LMS.handler.EntityNotFoundException;
import org.saima.LMS.model.Course;
import org.saima.LMS.model.Enrollment;
import org.saima.LMS.model.User;
import org.saima.LMS.repository.CourseRepository;
import org.saima.LMS.repository.EnrollmentRepository;
import org.saima.LMS.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class EnrollmentService {

	@Autowired
	private EnrollmentRepository enrollmentRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private CourseRepository courseRepository;

	private EnrollmentDTO mapToDTO(Enrollment enrollment) {
		return new EnrollmentDTO(enrollment.getId(), enrollment.getCourse().getCourseName(), enrollment.getName(),
				enrollment.getEmail(), enrollment.getPhone(), enrollment.getEnrollmentDate(),
				enrollment.getPaymentMethod(), enrollment.getStatus());
	}

	private Enrollment mapToEntity(EnrollmentDTO enrollmentDTO) {
		Course course = courseRepository.findByCourseName(enrollmentDTO.getCourseName())
				.orElseThrow(() -> new RuntimeException("Course not found"));
		return new Enrollment(course, enrollmentDTO.getName(), enrollmentDTO.getEmail(), enrollmentDTO.getPhone(),
				enrollmentDTO.getEnrollmentDate(), enrollmentDTO.getPaymentMethod(), enrollmentDTO.getStatus());
	}

	public List<EnrollmentDTO> getAllEnrollments() {
		return enrollmentRepository.findAll().stream().map(this::mapToDTO).collect(Collectors.toList());
	}

	public EnrollmentDTO getEnrollmentById(Long id) {
		Enrollment enrollment = enrollmentRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Enrollment not found"));
		return mapToDTO(enrollment);
	}

	public List<EnrollmentDTO> getEnrollmentsByCourseName(String courseName) {
		return enrollmentRepository.findByCourse_CourseName(courseName).stream().map(this::mapToDTO)
				.collect(Collectors.toList());
	}

	@Transactional
	public EnrollmentDTO updateStatus(Long id, String status) {
		Enrollment enrollment = enrollmentRepository.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("Enrollment not found with ID: " + id));

		try {
			EnrollmentStatus newStatus = EnrollmentStatus.valueOf(status.toUpperCase());
			enrollment.setStatus(newStatus);
			enrollmentRepository.save(enrollment);

			if (EnrollmentStatus.APPROVED.equals(newStatus)) {
				Optional<User> byEmail = userRepository.findByEmail(enrollment.getEmail());
				if (byEmail.isEmpty()) {
					User user = new User();

					user.setName(enrollment.getName());
					user.setEmail(enrollment.getEmail());
					user.setPhone(enrollment.getPhone());
					user.setRole(Role.STUDENT);

					String rawPassword = generateRandomPassword(8);
					log.info("Generated password for user {} is [{}]", user.getEmail(), rawPassword);
					String encodedPassword = passwordEncoder.encode(rawPassword);

					user.setPassword(encodedPassword);

					userRepository.save(user);
					// TODO: after user is saved send mail to users email with password for login
				}
			}

			return mapToDTO(enrollment);
		} catch (IllegalArgumentException e) {
			throw new IllegalArgumentException("Invalid status value: " + status);
		}
	}

	public EnrollmentDTO createEnrollment(EnrollmentDTO enrollmentDTO) {
		Enrollment enrollment = mapToEntity(enrollmentDTO);
		return mapToDTO(enrollmentRepository.save(enrollment));
	}

	public EnrollmentDTO updateEnrollment(Long id, EnrollmentDTO enrollmentDTO) {
		Enrollment existingEnrollment = enrollmentRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Enrollment not found"));

		existingEnrollment.setName(enrollmentDTO.getName());
		existingEnrollment.setEmail(enrollmentDTO.getEmail());
		existingEnrollment.setPhone(enrollmentDTO.getPhone());
		existingEnrollment.setEnrollmentDate(enrollmentDTO.getEnrollmentDate());
		existingEnrollment.setPaymentMethod(enrollmentDTO.getPaymentMethod());
		existingEnrollment.setStatus(enrollmentDTO.getStatus());

		return mapToDTO(enrollmentRepository.save(existingEnrollment));
	}

	public void deleteEnrollment(Long id) {
		enrollmentRepository.deleteById(id);
	}

	private String generateRandomPassword(int length) {
		String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*";
		StringBuilder password = new StringBuilder();
		Random random = new Random();

		for (int i = 0; i < length; i++) {
			password.append(chars.charAt(random.nextInt(chars.length())));
		}

		return password.toString();
	}
}
