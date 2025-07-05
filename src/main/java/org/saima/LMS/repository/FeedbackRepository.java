package org.saima.LMS.repository;

import java.util.List;

import org.saima.LMS.dto.FeedbackWithUserNameDTO;
import org.saima.LMS.model.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface FeedbackRepository extends JpaRepository<Feedback, Long> {
//	  List<Feedback> findByUserName(String name);

	@Query("SELECT new org.saima.LMS.dto.FeedbackWithUserNameDTO(f.id, f.content, f.rating, f.createdAt, f.updatedAt, u.name, u.avatarUrl) "
			+ "FROM Feedback f JOIN f.student u")
	List<FeedbackWithUserNameDTO> fetchFeedbackWithStudentInfo();

}
