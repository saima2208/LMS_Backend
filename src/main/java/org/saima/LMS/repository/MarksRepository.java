package org.saima.LMS.repository;

import java.util.List;

import org.saima.LMS.model.Marks;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MarksRepository extends JpaRepository<Marks,Long>{
//List<Marks>getMarkstByAssignmentIdAndUserId(Long AssignmentId, Long userId);

@Query("SELECT m FROM Marks m WHERE m.assignment.id = :assignmentId AND m.student.id = :userId")
List<Marks> getMarkstByAssignmentIdAndUserId(@Param("assignmentId") Long assignmentId, @Param("userId") Long userId);

}
