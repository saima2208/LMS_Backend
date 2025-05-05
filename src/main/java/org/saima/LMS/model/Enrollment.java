package org.saima.LMS.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
@Table(name = "enrollments")
public class Enrollment {
	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    @ManyToOne
	    @JoinColumn(name = "student_id", nullable = false)
	    private User studentId;

	    @ManyToOne
	    @JoinColumn(name = "course_id", nullable = false)
	    private Course course;

	    @Column(name = "enrollment_date", nullable = false)
	    private LocalDateTime enrollmentDate;
	    
	    private String paymentMethod;

	    public Enrollment(User studentId, Course course, LocalDateTime enrollmentDate,String paymentMethod) {
	        this.studentId = studentId;
	        this.course = course;
	        this.enrollmentDate = enrollmentDate;
	        this.paymentMethod = paymentMethod;
	    }

}
