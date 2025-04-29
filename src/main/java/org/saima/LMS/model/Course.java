package org.saima.LMS.model;

import java.time.LocalDateTime;
import java.util.List;

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
	@JoinColumn(name = "teacher", nullable = false, referencedColumnName = "user_id")
	private User teacher;

	@OneToMany(mappedBy = "course", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Lesson> lessons;


	@Column(nullable = false, name = "TITLE")
	private String name;


	private String price;
	private LocalDateTime startDate;
	private Long duration;

	@Lob
	private String description;


	public Course(User teacher, List<Lesson> lessons, String name, String price, Long duration,
				  LocalDateTime startDate, String description) {
		this.teacher = teacher;
		this.lessons = lessons;
		this.name = name;
		this.price = price;
		this.startDate = startDate;
		this.duration = duration;
		this.description = description;
	}

}
