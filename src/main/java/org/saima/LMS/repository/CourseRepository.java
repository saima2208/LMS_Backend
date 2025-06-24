package org.saima.LMS.repository;

import java.util.List;
import java.util.Optional;

import org.saima.LMS.model.Course;
import org.saima.LMS.model.Lesson;
import org.saima.LMS.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
	
	 List<Course> findCourseByTeacher(User teacher);

	  boolean existsByCourseName(String courseName);

	  Optional<Course> findByCourseName(String courseName);
	  
	  @Query("SELECT u FROM User u JOIN u.courses c WHERE c.id = :courseId")
	  List<User> findUsersByCourseId(@Param("courseId") Long courseId);


}