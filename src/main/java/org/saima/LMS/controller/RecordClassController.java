package org.saima.LMS.controller;

import java.util.List;

import org.saima.LMS.dto.RecordClassDTO;
import org.saima.LMS.model.RecordClass;
import org.saima.LMS.service.RecordClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/record-classes")
@CrossOrigin(origins = "http://localhost:4200")
public class RecordClassController {

	 @Autowired
	    private RecordClassService recordClassService;

	    @PostMapping
	    public ResponseEntity<RecordClass> createRecordClass(@Validated @RequestBody RecordClassDTO recordClassDTO) {
	        RecordClass createdRecordClass = recordClassService.createRecordClass(recordClassDTO);
	        return ResponseEntity.ok(createdRecordClass);
	    }

	    @GetMapping
	    public ResponseEntity<List<RecordClass>> getAllRecordClasses() {
	        return ResponseEntity.ok(recordClassService.getAllRecordClasses());
	    }

	    @GetMapping("/{id}")
	    public ResponseEntity<RecordClass> getRecordClassById(@PathVariable Long id) {
	        return ResponseEntity.ok(recordClassService.getRecordClassById(id));
	    }

	    @DeleteMapping("/{id}")
	    public ResponseEntity<Void> deleteRecordClass(@PathVariable Long id) {
	        recordClassService.deleteRecordClass(id);
	        return ResponseEntity.noContent().build();
	    }
	    
	    @GetMapping("/course/{courseId}")
	    public ResponseEntity<List<RecordClass>> getRecordClassesByCourseId(@PathVariable Long courseId) {
	        return ResponseEntity.ok(recordClassService.getRecordClassesByCourseId(courseId));
	    }

}
