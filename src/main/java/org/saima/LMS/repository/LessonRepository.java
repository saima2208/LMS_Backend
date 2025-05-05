package org.saima.LMS.repository;

import org.saima.LMS.model.Lesson;
import org.saima.LMS.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LessonRepository extends JpaRepository<Lesson, Long> {
    // Find lessons by course
    List<Lesson> findByCourse(Course course);
}
