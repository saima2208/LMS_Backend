package org.saima.LMS.model;

import jakarta.persistence.*;
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
public class Lesson {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long lesson_id;

	@ManyToOne
	@JoinColumn(name = "course_id", referencedColumnName = "course_id", nullable = false)
	private Course course;

	@Column(unique = true, nullable = false, name = "TITLE")
	private String topic;

	@Lob
	private String description;


	public Lesson(Course course, String topic, String description) {

		this.course = course;
		this.topic = topic;
		this.description = description;

	}

}
