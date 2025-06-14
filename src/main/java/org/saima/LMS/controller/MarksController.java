package org.saima.LMS.controller;

import java.util.List;

import org.saima.LMS.dto.MarksDTO;
import org.saima.LMS.model.Marks;
import org.saima.LMS.service.MarksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

@GetMapping
public ResponseEntity<List<Marks>> getAllMarks() {
    List<Marks> marksList = marksService.getAllMarks();
    return ResponseEntity.ok(marksList);
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
