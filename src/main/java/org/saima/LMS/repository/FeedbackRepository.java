package org.saima.LMS.repository;




import org.saima.LMS.model.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FeedbackRepository extends JpaRepository<Feedback, Long> {
//	  List<Feedback> findByUserName(String name);
}
