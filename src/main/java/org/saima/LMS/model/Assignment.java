package org.saima.LMS.model;

import java.time.LocalDate;

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
@Table(name = "t_assignment")
public class Assignment {
	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    @ManyToOne
	    @JoinColumn(name = "course_id", nullable = false)
	    private Course course;

	    @Column(nullable = false, length = 150)
	    private String topic;

	    @Column(columnDefinition = "TEXT")
	    private LocalDate dueDate;

	 
	    
	    public Assignment(Course course, String topic, LocalDate dueDate) {

			this.course = course;
			this.topic = topic;
			this.dueDate = dueDate;

		}

}
