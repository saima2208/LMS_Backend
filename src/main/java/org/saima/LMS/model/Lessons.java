package org.saima.LMS.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
@Table(name = "T_LESSONS")
public class Lessons {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long lesson_id;

	@ManyToOne
	@JoinColumn(name = "course_id", referencedColumnName = "course_id", nullable = false) 
	private Course course;

	@Column(unique = true, nullable = false, name = "TITLE")
	private String topic;
	private String description;


	public Lessons(Course course, String topic, String description) {

		this.course = course;
		this.topic = topic;
		this.description = description;

	}

}
