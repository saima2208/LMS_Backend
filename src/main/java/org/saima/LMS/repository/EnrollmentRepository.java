package org.saima.LMS.repository;

import java.util.List;

import org.saima.LMS.model.Enrollment;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {
    List<Enrollment> findByCourse_CourseName(String courseName);
}
