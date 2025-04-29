package org.saima.LMS.model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
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
@Table(name = "T_FEEDBACK")
public class Feedback {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long feedback_id;

	@ManyToOne
	@JoinColumn(name = "course_id", nullable = false) 
	private Course course;

	
	@ManyToOne
	@JoinColumn(name = "student_id", nullable = false) 
	private User student;

  @Lob
	private String description;

	private LocalDateTime publishDate;

	public Feedback(Course course, User student, String description) {
		this.course = course;
		this.student = student;
		this.description = description;
	}
}
