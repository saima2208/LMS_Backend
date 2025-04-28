//package org.saima.LMS.model;
//
//import java.time.LocalDateTime;
//
//import org.saima.LMS.constants.SubmissionStatus;
//
//import jakarta.persistence.Entity;
//import jakarta.persistence.EnumType;
//import jakarta.persistence.Enumerated;
//import jakarta.persistence.GeneratedValue;
//import jakarta.persistence.GenerationType;
//import jakarta.persistence.Id;
//import jakarta.persistence.JoinColumn;
//import jakarta.persistence.ManyToOne;
//import jakarta.persistence.Table;
//import lombok.AllArgsConstructor;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;
//
//@Getter
//@Setter
//@Entity
//@NoArgsConstructor
//@AllArgsConstructor
//@Table(name = "T_ASSIGNMENT_SUBMISSION")
//public class AssignmentSubmission {
//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	private Long submissionId;
//
//	
//	@ManyToOne
//	@JoinColumn(name = "assignment_id", nullable = false)
//	private Assignment assignment;
//
//	
//	@ManyToOne
//	@JoinColumn(name = "student_id", nullable = false)
//	private User student;
//
//	private LocalDateTime submissionDate;
//	private String fileUrl; 
//	private Integer grade;
//
//	@Enumerated(EnumType.STRING)
//	private SubmissionStatus status;
//
//
//
//	public AssignmentSubmission(Assignment assignment, User student, String fileUrl, Integer grade,
//			SubmissionStatus status) {
//		this.assignment = assignment;
//		this.student = student;
//		this.fileUrl = fileUrl;
//		this.grade = grade;
//		this.status = status;
//		this.submissionDate = LocalDateTime.now();
//	}
//}
