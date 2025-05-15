package org.saima.LMS.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
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
@Table(name = "t_courses")
public class Course {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@OneToOne
	@JoinColumn(name = "teacher_id")
	private User teacher;

//	@OneToMany(mappedBy = "course")
//	private List<Lesson> lessons;

	@Column(nullable = false, length = 200, unique = true)
	private String courseName;

	private String price;

	private LocalDate startDate;

	@Column(nullable = false, name = "duration(Months)")
	private String duration;

	@Column(columnDefinition = "TEXT")
	private String description;
	@Column(length = 500)
	private String image;

	public Course(User teacher, String courseName, String price, LocalDate startDate, String duration, String description,
			String image) {
		this.teacher = teacher;
//		this.lessons = lessons;
		this.courseName = courseName;
		this.price = price;
		this.startDate = startDate;
		this.duration = duration;
		this.description = description;
		this.image = image;
	}

}
