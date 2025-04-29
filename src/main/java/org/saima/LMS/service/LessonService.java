//package org.saima.LMS.service;
//
//import org.saima.LMS.model.Lesson;
//import org.saima.LMS.model.Course;
//import org.saima.LMS.repository.LessonRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//import java.util.Optional;
//
//@Service
//public class LessonService {
//
//    @Autowired
//    private LessonRepository lessonRepository;
//
//    // Create a new lesson
//    public Lesson createLesson(Lesson lesson) {
//        return lessonRepository.save(lesson);
//    }
//
//    // Retrieve all lessons
//    public List<Lesson> getAllLessons() {
//        return lessonRepository.findAll();
//    }
//
//    // Retrieve lessons by course
//    public List<Lesson> getLessonsByCourse(Course course) {
//        return lessonRepository.findByCourse(course);
//    }
//
//    // Retrieve a specific lesson by ID
//    public Optional<Lesson> getLessonById(Long id) {
//        return lessonRepository.findById(id);
//    }
//
//    // Update an existing lesson
//    public Lesson updateLesson(Long id, Lesson lessonDetails) {
//        return lessonRepository.findById(id)
//                .map(lesson -> {
//                    lesson.setTopic(lessonDetails.getTopic());
//                    lesson.setDescription(lessonDetails.getDescription());
//                    return lessonRepository.save(lesson);
//                })
//                .orElseThrow(() -> new RuntimeException("Lesson not found with id: " + id));
//    }
//
//    // Delete a lesson
//    public void deleteLesson(Long id) {
//        lessonRepository.deleteById(id);
//    }
//}
