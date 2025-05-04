package org.saima.LMS.model;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.CascadeType;
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
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "T_COURSE")
public class Course {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long course_id;

	@OneToOne
	@JoinColumn(name = "teacher", nullable = false, referencedColumnName = "userId")
	private User teacher; // private Teacher teacher;

	@OneToMany(mappedBy = "course", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Lesson> lessons;

	@Column(nullable = false, unique = true, name = "TITLE")
	private String name;

	private String price;
	private LocalDateTime startDate;
	private String duration;

	private String description;

	public Course(User teacher, List<Lesson> lessons, String name, String price, String duration,
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
