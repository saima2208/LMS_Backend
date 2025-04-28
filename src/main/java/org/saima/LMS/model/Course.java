package org.saima.LMS.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
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
@Table(name = "T_COURSE")
public class Course {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long course_id;

	@OneToOne
	@JoinColumn(name = "teacher", nullable = false, referencedColumnName = "id")
	private User teacher;

	private Long total_lesson;

	@Column(nullable = false, name = "TITLE")
	private String name;

	private String description;
	private String price;
	private LocalDateTime startDate;
	private Long duration;
	private String image;

	public Course(User teacher, Long total_lesson, String name, String description, String price, Long duration,
			LocalDateTime startDate, String image) {
		this.teacher = teacher;
		this.name = name;
		this.total_lesson = total_lesson;
		this.description = description;

		this.startDate = startDate;
		this.price = price;
		this.duration = duration;
		this.image = image;

	}

}
