package org.saima.LMS.repository;

import java.util.List;

import org.saima.LMS.model.AssignmentSubmission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AssignmentSubmissionRepository extends JpaRepository<AssignmentSubmission, Long>{
	  boolean existsByAssignmentIdAndStudentId(Long assignmentId, Long studentId);
	  
	  List<AssignmentSubmission> findByAssignmentId(Long assignmentId);

}
