//package org.saima.LMS.model;
//
//import java.time.LocalDateTime;
//
//import jakarta.persistence.Entity;
//import jakarta.persistence.GeneratedValue;
//import jakarta.persistence.GenerationType;
//import jakarta.persistence.Id;
//import jakarta.persistence.JoinColumn;
//import jakarta.persistence.ManyToMany;
//import jakarta.persistence.ManyToOne;
//import jakarta.persistence.Table;
//import lombok.AllArgsConstructor;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;
//
//@Getter
//@Setter
//@Entity
//@NoArgsConstructor
//@AllArgsConstructor
//@Table(name = "T_ENROLLMENT")
//public class Enrollments {
//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	private Long enrollment_id;
//
//	@ManyToOne
//	@JoinColumn(name = "course_id", nullable = false, referencedColumnName = "id") 
//	private Course course;
//
//	
//	@ManyToMany
//	@JoinColumn(name = "student_id", nullable = false,referencedColumnName = "id") 
//	private User student;
//
//	private LocalDateTime enrolled_date;
//	private String status;
//
//	public Enrollments(Course course, User student, String status) {
//		this.course = course;
//		this.student = student;
//		this.status = status;
//	}
//}
