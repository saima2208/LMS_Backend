package org.saima.LMS.model;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
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
@Table(name = "t_courses")
public class Course {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@OneToOne
	@JoinColumn(name = "teacher_id")
	private User teacher;

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

	@JsonIgnore
	@ManyToMany(mappedBy = "courses", fetch = FetchType.LAZY)
	private Set<User> users = new HashSet<>();

	public Course(User teacher, String courseName, String price, LocalDate startDate, String duration,
			String description, String image) {
		this.teacher = teacher;
		this.courseName = courseName;
		this.price = price;
		this.startDate = startDate;
		this.duration = duration;
		this.description = description;
		this.image = image;
	}

}
