package org.saima.LMS.model;

import java.time.LocalDateTime;

import org.saima.LMS.constants.AssignmentStatus;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "T_ASSIGNMENT")
public class Assignment {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long assignmentId;

	// Many-to-One relationship with Course
	@ManyToOne
	@JoinColumn(name = "course_id", nullable = false)
	private Course course;

	private String title;

	private String description;

	// The due date of the assignment
	private LocalDateTime dueDate;

	// The status of the assignment (Pending, Submitted, Graded)
	@Enumerated(EnumType.STRING)
	private AssignmentStatus status;

	private LocalDateTime createdAt;

	public Assignment(Course course, String title, String description, LocalDateTime dueDate, AssignmentStatus status) {
		this.course = course;
		this.title = title;
		this.description = description;
		this.dueDate = dueDate;
		this.status = status;
		this.createdAt = LocalDateTime.now(); // set creation timestamp
	}

}
