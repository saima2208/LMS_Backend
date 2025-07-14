package org.saima.LMS.repository;

import java.util.List;

import org.saima.LMS.model.RecordClass;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecordClassRepository extends JpaRepository<RecordClass,Long> {
	 List<RecordClass> findByCourseId(Long courseId);
}
