//package org.saima.LMS.controller;
//
//import org.saima.LMS.model.Course;
//import org.saima.LMS.model.Lesson;
//import org.saima.LMS.service.LessonService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/api/lessons")
//public class LessonController {
//
//    @Autowired
//    private LessonService lessonService;
//
//    // Create a new lesson
//    @PostMapping
//    public ResponseEntity<Lesson> createLesson(@RequestBody Lesson lesson) {
//        return new ResponseEntity<>(lessonService.createLesson(lesson), HttpStatus.CREATED);
//    }
//
//    // Retrieve all lessons
//    @GetMapping
//    public ResponseEntity<List<Lesson>> getAllLessons() {
//        return new ResponseEntity<>(lessonService.getAllLessons(), HttpStatus.OK);
//    }
//
//    // Retrieve lessons by course ID
//    @GetMapping("/course/{courseId}")
//    public ResponseEntity<List<Lesson>> getLessonsByCourse(@PathVariable Long courseId) {
//        Course course = new Course();
//        course.setCourse_id(courseId); // Temporary object for reference
//        return new ResponseEntity<>(lessonService.getLessonsByCourse(course), HttpStatus.OK);
//    }
//
//    // Retrieve a lesson by ID
//    @GetMapping("/{id}")
//    public ResponseEntity<Lesson> getLessonById(@PathVariable Long id) {
//        return lessonService.getLessonById(id)
//                .map(lesson -> new ResponseEntity<>(lesson, HttpStatus.OK))
//                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
//    }
//
//    // Update a lesson
//    @PutMapping("/{id}")
//    public ResponseEntity<Lesson> updateLesson(@PathVariable Long id, @RequestBody Lesson lessonDetails) {
//        return new ResponseEntity<>(lessonService.updateLesson(id, lessonDetails), HttpStatus.OK);
//    }
//
//    // Delete a lesson
//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> deleteLesson(@PathVariable Long id) {
//        lessonService.deleteLesson(id);
//        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//    }
//}
