//package org.saima.LMS.service;
//
//import java.util.List;
//import java.util.stream.Collectors;
//
//import org.saima.LMS.dto.EnrollmentDTO;
//import org.saima.LMS.handler.CourseNotFoundException;
//import org.saima.LMS.model.Course;
//import org.saima.LMS.model.Enrollment;
//import org.saima.LMS.repository.CourseRepository;
//import org.saima.LMS.repository.EnrollmentRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//@Service
//public class EnrollmentService {
//
//    @Autowired
//    private EnrollmentRepository enrollmentRepository;
//
//    @Autowired
//    private CourseRepository courseRepository;
//
//    private EnrollmentDTO mapToDTO(Enrollment enrollment) {
//        return new EnrollmentDTO(
//                enrollment.getId(),
//                enrollment.getCourse().getCourseName(),
//                enrollment.getName(),
//                enrollment.getEmail(),
//                enrollment.getPhone(),
//                enrollment.getEnrollmentDate(),
//                enrollment.getPaymentMethod(),
//                enrollment.getStatus()
//        );
//    }
//
////    private Enrollment mapToEntity(EnrollmentDTO enrollmentDTO) {
////        Course course = courseRepository.findByName(enrollmentDTO.getCourseName())
////                .orElseThrow(() -> new RuntimeException("Course not found"));
////        return new Enrollment(
////                course,
////                enrollmentDTO.getName(),
////                enrollmentDTO.getEmail(),
////                enrollmentDTO.getPhone(),
////                enrollmentDTO.getEnrollmentDate(),
////                enrollmentDTO.getPaymentMethod(),
////                enrollmentDTO.getStatus()
////        );
////    }
//    
//    
//    private Enrollment mapToEntity(EnrollmentDTO enrollmentDTO) {
//        if (enrollmentDTO.getCourseName() == null || enrollmentDTO.getCourseName().isEmpty()) {
//            throw new IllegalArgumentException("Course name cannot be null or empty");
//        }
//
//        Course course = courseRepository.findByName(enrollmentDTO.getCourseName())
//                .orElseThrow(() -> new CourseNotFoundException("Course not found with name: " + enrollmentDTO.getCourseName()));
//
//        return new Enrollment(
//                course,
//                enrollmentDTO.getName(),
//                enrollmentDTO.getEmail(),
//                enrollmentDTO.getPhone(),
//                enrollmentDTO.getEnrollmentDate(),
//                enrollmentDTO.getPaymentMethod(),
//                enrollmentDTO.getStatus()
//        );
//    }
//
//    
//    public List<Enrollment> getEnrollmentsByCourseName(String courseName) {
//        return enrollmentRepository.findByCourseName(courseName);
//    }
//
//    public EnrollmentDTO createEnrollment(EnrollmentDTO enrollmentDTO) {
//        Enrollment enrollment = mapToEntity(enrollmentDTO);
//        return mapToDTO(enrollmentRepository.save(enrollment));
//    }
//
//    public EnrollmentDTO getEnrollmentById(Long id) {
//        Enrollment enrollment = enrollmentRepository.findById(id)
//                .orElseThrow(() -> new RuntimeException("Enrollment not found"));
//        return mapToDTO(enrollment);
//    }
//
//    public List<EnrollmentDTO> getAllEnrollments() {
//        return enrollmentRepository.findAll()
//                .stream()
//                .map(this::mapToDTO)
//                .collect(Collectors.toList());
//    }
//
//    public EnrollmentDTO updateEnrollment(Long id, EnrollmentDTO enrollmentDTO) {
//        Enrollment existingEnrollment = enrollmentRepository.findById(id)
//                .orElseThrow(() -> new RuntimeException("Enrollment not found"));
//
//        existingEnrollment.setName(enrollmentDTO.getName());
//        existingEnrollment.setEmail(enrollmentDTO.getEmail());
//        existingEnrollment.setPhone(enrollmentDTO.getPhone());
//        existingEnrollment.setEnrollmentDate(enrollmentDTO.getEnrollmentDate());
//        existingEnrollment.setPaymentMethod(enrollmentDTO.getPaymentMethod());
//        existingEnrollment.setStatus(enrollmentDTO.getStatus());
//
//        return mapToDTO(enrollmentRepository.save(existingEnrollment));
//    }
//
//    public void deleteEnrollment(Long id) {
//        enrollmentRepository.deleteById(id);
//    }
//}
//
//
//



package org.saima.LMS.service;

import java.util.List;
import java.util.stream.Collectors;

import org.saima.LMS.constants.EnrollmentStatus;
import org.saima.LMS.dto.EnrollmentDTO;
import org.saima.LMS.handler.EntityNotFoundException;
import org.saima.LMS.model.Course;
import org.saima.LMS.model.Enrollment;
import org.saima.LMS.repository.CourseRepository;
import org.saima.LMS.repository.EnrollmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EnrollmentService {

    @Autowired
    private EnrollmentRepository enrollmentRepository;

    @Autowired
    private CourseRepository courseRepository;

    private EnrollmentDTO mapToDTO(Enrollment enrollment) {
        return new EnrollmentDTO(
                enrollment.getId(),
                enrollment.getCourse().getCourseName(),
                enrollment.getName(),
                enrollment.getEmail(),
                enrollment.getPhone(),
                enrollment.getEnrollmentDate(),
                enrollment.getPaymentMethod(),
                enrollment.getStatus()
        );
    }

    private Enrollment mapToEntity(EnrollmentDTO enrollmentDTO) {
        Course course = courseRepository.findByCourseName(enrollmentDTO.getCourseName())
                .orElseThrow(() -> new RuntimeException("Course not found"));
        return new Enrollment(
                course,
                enrollmentDTO.getName(),
                enrollmentDTO.getEmail(),
                enrollmentDTO.getPhone(),
                enrollmentDTO.getEnrollmentDate(),
                enrollmentDTO.getPaymentMethod(),
                enrollmentDTO.getStatus()
        );
    }

    public List<EnrollmentDTO> getAllEnrollments() {
        return enrollmentRepository.findAll()
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public EnrollmentDTO getEnrollmentById(Long id) {
        Enrollment enrollment = enrollmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Enrollment not found"));
        return mapToDTO(enrollment);
    }

    public List<EnrollmentDTO> getEnrollmentsByCourseName(String courseName) {
        return enrollmentRepository.findByCourse_CourseName(courseName)
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }
    
    
    public EnrollmentDTO updateStatus(Long id, String status) {
        Enrollment enrollment = enrollmentRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Enrollment not found with ID: " + id));

        try {
            EnrollmentStatus newStatus = EnrollmentStatus.valueOf(status.toUpperCase());
            enrollment.setStatus(newStatus);
            enrollmentRepository.save(enrollment);
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
}

