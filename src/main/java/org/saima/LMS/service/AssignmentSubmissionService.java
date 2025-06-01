package org.saima.LMS.service;

import org.saima.LMS.dto.SubmitAssignmentDTO;
import org.saima.LMS.model.Assignment;
import org.saima.LMS.model.AssignmentSubmission;
import org.saima.LMS.model.User;
import org.saima.LMS.repository.AssignmentRepository;
import org.saima.LMS.repository.AssignmentSubmissionRepository;
import org.saima.LMS.repository.UserRepository;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

@Service
public class AssignmentSubmissionService {

    private final AssignmentSubmissionRepository submissionRepository;
    private final AssignmentRepository assignmentRepository;
    private final UserRepository userRepository;
    private final FileStorageService fileStorageService;

    public AssignmentSubmissionService(AssignmentSubmissionRepository submissionRepository,
                                       AssignmentRepository assignmentRepository,
                                       UserRepository userRepository,
                                       FileStorageService fileStorageService) {
        this.submissionRepository = submissionRepository;
        this.assignmentRepository = assignmentRepository;
        this.userRepository = userRepository;
        this.fileStorageService = fileStorageService;
    }

    @Transactional
    public SubmitAssignmentDTO submitAssignment(SubmitAssignmentDTO dto) {
        // Check if the student has already submitted for this assignment
        if (submissionRepository.existsByAssignmentIdAndStudentId(dto.getAssginmentId(), dto.getStudentId())) {
            throw new IllegalArgumentException("You have already submitted this assignment");
        }

        // Validate file size
        MultipartFile file = dto.getFile();
        if (file.getSize() > 10 * 1024 * 1024) { // 10MB limit
            throw new IllegalArgumentException("File size exceeds the 10MB limit");
        }

        // Store the file
        String fileUrl = fileStorageService.store(file);

        // Fetch student and assignment entities
        User student = userRepository.findById(dto.getStudentId())
                .orElseThrow(() -> new IllegalArgumentException("Student not found with ID: " + dto.getStudentId()));
        Assignment assignment = assignmentRepository.findById(dto.getAssginmentId())
                .orElseThrow(() -> new IllegalArgumentException("Assignment not found with ID: " + dto.getAssginmentId()));

        // Create and save the submission
        AssignmentSubmission submission = new AssignmentSubmission(assignment, student, null, fileUrl);
        submissionRepository.save(submission);

        // Return the response DTO
        dto.setFile(null); // Remove file from the response for security reasons
        return dto;
    }

    public Resource getAssignmentFile(Long submissionId) {
        // Fetch submission by ID
        AssignmentSubmission submission = submissionRepository.findById(submissionId)
                .orElseThrow(() -> new IllegalArgumentException("Submission not found with ID: " + submissionId));

        // Load the file as a resource
        String filePath = submission.getFileUrl();
        Resource resource = fileStorageService.loadAsResource(filePath);

        if (resource == null || !resource.exists() || !resource.isReadable()) {
            throw new IllegalArgumentException("Could not read file: " + filePath);
        }

        return resource;
    }
}
