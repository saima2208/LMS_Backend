//package org.saima.LMS.model;
//
//import java.time.LocalDate;
//
//import org.saima.LMS.constants.EnrollmentStatus;
//
//import jakarta.persistence.Column;
//import jakarta.persistence.Entity;
//import jakarta.persistence.EnumType;
//import jakarta.persistence.Enumerated;
//import jakarta.persistence.GeneratedValue;
//import jakarta.persistence.GenerationType;
//import jakarta.persistence.Id;
//import jakarta.persistence.JoinColumn;
//import jakarta.persistence.ManyToOne;
//import jakarta.persistence.Table;
//import lombok.AllArgsConstructor;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;
//
//@Getter
//@Setter
//@AllArgsConstructor
//@NoArgsConstructor
//@Entity
//@Table(name = "enrollments")
//public class Enrollment {
//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	private Long id;
//
//	@ManyToOne
//	@JoinColumn(name = "Course_name", nullable = false)
//	private Course course;
//	
//	@Column(name = "Student_Name", nullable = false)
//	private String name;
//	
//	@Column( nullable = false)
//	private String email;
//	
//	private String phone;
//	 
//
//	@Column(name = "enrollment_date", nullable = false)
//	private LocalDate enrollmentDate;
//
//	private String paymentMethod;
//	
//	 @Enumerated(EnumType.STRING)
//	    @Column(nullable = false)
//	    private EnrollmentStatus status;
//	
//
//	public Enrollment(
//			
//			Course course,
//			String name,
//			String email,
//			String phone,
//			LocalDate enrollmentDate,
//			String paymentMethod,
//			EnrollmentStatus status
//			) {
//
//		this.course = course;
//		this.name = name;
//		this.email = email;
//		this.phone = phone;
//		this.enrollmentDate = enrollmentDate;
//		this.paymentMethod = paymentMethod;
//		this.status = status;
//	}
//
//}



package org.saima.LMS.model;

import java.time.LocalDate;

import org.saima.LMS.constants.EnrollmentStatus;

import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "enrollments")
public class Enrollment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "course_name", nullable = false)
    private Course course;

    @Column(nullable = false,name = "student_name")
    private String name;

    @Column(nullable = false)
    private String email;

    private String phone;

    @Column(name = "enrollment_date", nullable = false)
    private LocalDate enrollmentDate;

    private String paymentMethod;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EnrollmentStatus status;
    
    

    public Enrollment(Course course, String name, String email, String phone, LocalDate enrollmentDate, 
            String paymentMethod, EnrollmentStatus status) {
this.course = course;
this.name = name;
this.email = email;
this.phone = phone;
this.enrollmentDate = enrollmentDate;
this.paymentMethod = paymentMethod;
this.status = status;
}

}

