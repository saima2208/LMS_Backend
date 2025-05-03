package org.saima.LMS.repository;

import java.util.Optional;

import org.saima.LMS.constants.Role;
import org.saima.LMS.model.Course;
import org.saima.LMS.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
//    List<Course> findByTeacher(User teacher);
//   List<Course> findByLesson(Lesson lesson);
	boolean existsByName(String name);

	Optional<User> findByUserIdAndRole(Long UserId, Role role);
}
