package org.saima.LMS.controller;

import org.saima.LMS.dto.MarksDTO;
import org.saima.LMS.model.Marks;
import org.saima.LMS.service.MarksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/marks")
public class MarksController {
    @Autowired
    private MarksService marksService;

    @PostMapping
    public ResponseEntity<Marks> addMarks(@RequestBody MarksDTO marksDTO) {
        Marks savedMarks = marksService.addMarks(marksDTO);
        return ResponseEntity.ok(savedMarks);
    }

    @PostMapping("/all")
    public ResponseEntity<List<Marks>> addAllMarks(@RequestBody List<MarksDTO> marksDTOList) {
        List<Marks> savedMarksList = marksService.addAllMarks(marksDTOList);
        return ResponseEntity.ok(savedMarksList);
    }

    @GetMapping
    public ResponseEntity<List<Marks>> getAllMarks() {
        List<Marks> marksList = marksService.getAllMarks();
        return ResponseEntity.ok(marksList);
    }
    
    @GetMapping("/{assignmentId}/{userId}")
    public ResponseEntity<List <Marks>> getByAssignmentIdAndUserId(@PathVariable Long assignmentId, @PathVariable Long userId) {
    	List<Marks> marks = marksService.getByAssignmentIdAndUserId (assignmentId,userId);
    	return ResponseEntity.ok(marks);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMarks(@PathVariable Long id) {
        marksService.deleteMarks(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Marks> updateMarks(@PathVariable Long id, @RequestBody MarksDTO marksDTO) {
        Marks updatedMarks = marksService.updateMarks(id, marksDTO);
        return ResponseEntity.ok(updatedMarks);
    }
}

 