package org.saima.LMS.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="t_Submitted_Assignment")
public class AssignmentSubmission {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name= "Assignment_id", nullable= false)
	private Assignment assignment;
	
	@ManyToOne
	@JoinColumn(name= "student_id", nullable= false)
	private User student;


	
	@Column(nullable = false)
	private LocalDateTime submitDate;
	
	@Column(nullable = false)
	private String fileUrl;
	
		
	public AssignmentSubmission( Assignment assignment, User student,LocalDateTime submitDate,String fileUrl) {
		
		this.assignment = assignment;
		this.student = student;
		this.submitDate = submitDate;
		this.fileUrl = fileUrl;
		
	}
	
	@PrePersist
	  protected void onCreate() {
		submitDate = LocalDateTime.now();
	      
	  }

//	  @PreUpdate
//	  protected void onUpdate() {
//	      updatedAt = LocalDateTime.now();
//	  }
}