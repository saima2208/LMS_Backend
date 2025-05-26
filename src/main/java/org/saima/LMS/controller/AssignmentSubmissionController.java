package org.saima.LMS.controller;

import java.time.LocalDate;

import org.saima.LMS.dto.SubmitAssignmentDTO;
import org.saima.LMS.service.AssignmentSubmissionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/submitAssignments")
public class AssignmentSubmissionController {

	private final AssignmentSubmissionService submissionService;

    public AssignmentSubmissionController(AssignmentSubmissionService submissionService) {
        this.submissionService = submissionService;
    }

    @PostMapping("/submit")
    public ResponseEntity<String> submitAssignment(
            @RequestParam("file") MultipartFile file,
            @RequestParam("studentId") Long studentId,
            @RequestParam("assginmentId") Long assignmentId,
            @RequestParam(value = "submitDate", required = false) LocalDate submitDate) {

        SubmitAssignmentDTO dto = new SubmitAssignmentDTO(studentId, assignmentId, submitDate);
        submissionService.submitAssignment(dto, file);
        return ResponseEntity.ok("Assignment submitted successfully!");
    }
}
