package org.saima.LMS.model;

import java.time.LocalDateTime;

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
@Table(name = "T_COURSE")
public class Course {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long course_id;

	@OneToOne
	@JoinColumn(name = "teacher", nullable = false, referencedColumnName = "id")
	private User teacher;
	
	@OneToMany
	@JoinColumn(name = "lesson", nullable = false, referencedColumnName = "lesson_id")
	private Lessons lesson;

	@Column(nullable = false, name = "TITLE")
	private String name;

	
	private String price;
	private LocalDateTime startDate;
	private Long duration;
	private String description;


	public Course(User teacher, Lessons lesson, String name, String price, Long duration,
			LocalDateTime startDate , String description) {
		this.teacher = teacher;
		this.name = name;
		this.lesson = lesson;
		

		this.startDate = startDate;
		this.price = price;
		this.duration = duration;
		this.description = description;


	}

}
