package org.saima.LMS.model;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
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
@Table(name = "t_course")
public class Course {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	
	@OneToOne
	@JoinColumn(name = "teacher_id", nullable = false)
	private User teacher;

//	@OneToMany(mappedBy = "course")
//	private List<Lesson> lessons;
	
	@Column(nullable = false, length = 200, unique = true)
	private String name;


	private String price;

	private LocalDateTime startDate;

	@Column(nullable = false, name = "duration(Months)")
	private String duration;

	@Column(columnDefinition = "TEXT")
	private String description;

	public Course(User teacher, String name, String price, LocalDateTime startDate,
			String duration, String description) {
		this.teacher = teacher;
//		this.lessons = lessons;
		this.name = name;
		this.price = price;
		this.startDate = startDate;
		this.duration = duration;
		this.description = description;
	}

}
