package org.saima.LMS.controller;

import org.saima.LMS.dto.LessonDTO;
import org.saima.LMS.model.Course;
import org.saima.LMS.model.Lesson;
import org.saima.LMS.service.LessonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/lessons")
@CrossOrigin(origins = "http://localhost:4200")
public class LessonController {

	 @Autowired
	    private LessonService lessonService;

	    @PostMapping
	    public ResponseEntity<Lesson> createLesson(@Validated @RequestBody LessonDTO lessonDTO) {
	        Lesson lesson = lessonService.createLesson(lessonDTO);
	        return ResponseEntity.ok(lesson);
	    }

	    @GetMapping
	    public ResponseEntity<List<Lesson>> getAllLessons() {
	        List<Lesson> lessons = lessonService.getAllLessons();
	        return ResponseEntity.ok(lessons);
	    }

	    @GetMapping("/{id}")
	    public ResponseEntity<Lesson> getLessonById(@PathVariable Long id) {
	        return lessonService.getLessonById(id)
	                .map(ResponseEntity::ok)
	                .orElse(ResponseEntity.notFound().build());
	    }

	    @DeleteMapping("/{id}")
	    public ResponseEntity<Void> deleteLesson(@PathVariable Long id) {
	        lessonService.deleteLesson(id);
	        return ResponseEntity.noContent().build();
	    }
}
