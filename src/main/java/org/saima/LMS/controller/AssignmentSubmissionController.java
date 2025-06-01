//package org.saima.LMS.controller;
//
//import java.time.LocalDate;
//import java.time.LocalDateTime;
//import java.util.List;
//
//import org.saima.LMS.dto.AssignmentDTO;
//import org.saima.LMS.dto.SubmitAssignmentDTO;
//import org.saima.LMS.model.Assignment;
//import org.saima.LMS.model.AssignmentSubmission;
//import org.saima.LMS.service.AssignmentSubmissionService;
//import org.springframework.core.io.Resource;
//import org.springframework.http.MediaType;
//import org.springframework.http.ResponseEntity;
//import org.springframework.validation.annotation.Validated;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.multipart.MultipartFile;
//
//import com.google.common.net.HttpHeaders;
//
//@RestController
//@RequestMapping("/api/submitAssignments")
//public class AssignmentSubmissionController {
//
//	private final AssignmentSubmissionService submissionService;
//
//    public AssignmentSubmissionController(AssignmentSubmissionService submissionService) {
//        this.submissionService = submissionService;
//    }
//    
// 
//    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
//    public ResponseEntity<String> submitAssignment(@ModelAttribute
//            @RequestParam Long studentId,
//            @RequestParam Long assignmentId,
//            @RequestParam MultipartFile file) {
//
//        SubmitAssignmentDTO dto = new SubmitAssignmentDTO();
//        dto.setStudentId(studentId);
//        dto.setAssginmentId(assignmentId);
//        dto.setFile(file);
//        
//        SubmitAssignmentDTO dto1 = submissionService.submitAssignment(dto1);
//        return ResponseEntity.ok(dto1);
//    }
//    
//    
//    
//    @GetMapping("/{id}/submit")
//    public ResponseEntity<List<Resource>> getSubmittedAssignment(@PathVariable Long id) {
//    	Resource file = submissionService.getAssignmentFile(id);
//    	return ResponseEntity.ok()
//    			.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename\"" + file.getFilename() + "\"")
//    			.body(file);
//    }
//    
//    
//
//
//}



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

    /**
     * Endpoint to submit an assignment.
     *
     * @param studentId    ID of the student submitting the assignment.
     * @param assignmentId ID of the assignment being submitted.
     * @param file         Assignment file to be submitted.
     * @return Success message or error.
     */
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

    /**
     * Endpoint to fetch a submitted assignment.
     *
     * @param id ID of the assignment submission.
     * @return The file as a downloadable resource.
     */
    @GetMapping("/{id}/submit")
    public ResponseEntity<Resource> getSubmittedAssignment(@PathVariable Long id) {
        Resource file = submissionService.getAssignmentFile(id);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(file);
    }
}

