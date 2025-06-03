package org.saima.LMS.controller;

import org.saima.LMS.dto.SubmitAssignmentDTO;
import org.saima.LMS.service.AssignmentSubmissionService;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/submitAssignments")
public class AssignmentSubmissionController {

    private final AssignmentSubmissionService submissionService;

    public AssignmentSubmissionController(AssignmentSubmissionService submissionService) {
        this.submissionService = submissionService;
    }
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> submitAssignment(
            @RequestParam Long studentId,
            @RequestParam Long assignmentId,
            @RequestParam MultipartFile file) {

        SubmitAssignmentDTO dto = new SubmitAssignmentDTO();
        dto.setStudentId(studentId);
        dto.setAssginmentId(assignmentId);
        dto.setFile(file);

        submissionService.submitAssignment(dto);

        return ResponseEntity.ok("Assignment submitted successfully.");
    }

    @GetMapping("/{id}/submit")
    public ResponseEntity<Resource> getSubmittedAssignment(@PathVariable Long id) {
        Resource file = submissionService.getAssignmentFile(id);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(file);
    }
}

