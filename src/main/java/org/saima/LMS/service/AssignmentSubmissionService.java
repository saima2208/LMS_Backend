package org.saima.LMS.service;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;

import org.saima.LMS.dto.SubmitAssignmentDTO;
import org.saima.LMS.model.Assignment;
import org.saima.LMS.model.AssignmentSubmission;
import org.saima.LMS.model.User;
import org.saima.LMS.repository.AssignmentRepository;
import org.saima.LMS.repository.AssignmentSubmissionRepository;
import org.saima.LMS.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class AssignmentSubmissionService {

	 private final AssignmentSubmissionRepository submissionRepository;
	    private final AssignmentRepository assignmentRepository;
	    private final UserRepository userRepository;

	    public AssignmentSubmissionService(AssignmentSubmissionRepository submissionRepository,
	                                       AssignmentRepository assignmentRepository,
	                                       UserRepository userRepository) {
	        this.submissionRepository = submissionRepository;
	        this.assignmentRepository = assignmentRepository;
	        this.userRepository = userRepository;
	    }

	    public void submitAssignment(SubmitAssignmentDTO dto, MultipartFile file) {
	        if (file == null || file.isEmpty()) {
	            throw new RuntimeException("File is required for submission");
	        }

	        String filePath = saveFile(file);

	        Assignment assignment = assignmentRepository.findById(dto.getAssginmentId())
	                .orElseThrow(() -> new RuntimeException("Assignment not found with ID: " + dto.getAssginmentId()));
	        User student = userRepository.findById(dto.getStudentId())
	                .orElseThrow(() -> new RuntimeException("Student not found with ID: " + dto.getStudentId()));

	        AssignmentSubmission submission = new AssignmentSubmission(assignment, student,
	                dto.getSubmitDate() != null ? dto.getSubmitDate() : LocalDate.now(), filePath);

	        submissionRepository.save(submission);
	    }

	    private String saveFile(MultipartFile file) {
	        try {
	            String directory = "uploads/";
	            String filePath = directory + file.getOriginalFilename();

	            File dir = new File(directory);
	            if (!dir.exists()) {
	                dir.mkdirs();
	            }

	            File dest = new File(filePath);
	            file.transferTo(dest);

	            return filePath;
	        } catch (IOException e) {
	            throw new RuntimeException("Failed to save file: " + e.getMessage());
	        }
	    }

}
