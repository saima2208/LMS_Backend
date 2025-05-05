package org.saima.LMS.repository;

import org.saima.LMS.model.Enrollment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {
    // Custom query methods can be added here if needed
}