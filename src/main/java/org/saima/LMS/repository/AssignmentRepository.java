package org.saima.LMS.repository;

import java.util.List;

import org.saima.LMS.model.Assignment;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AssignmentRepository extends JpaRepository<Assignment, Long>{
    List<Assignment> findByCourseId(Long courseId);
}
