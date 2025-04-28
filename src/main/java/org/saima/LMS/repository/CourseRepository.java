package org.saima.LMS.repository;

import org.saima.LMS.model.Course;
import org.saima.LMS.model.Lessons;
import org.saima.LMS.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
    List<Course> findByTeacher(User teacher);
   List<Course> findByLesson(Lessons lesson);
}

