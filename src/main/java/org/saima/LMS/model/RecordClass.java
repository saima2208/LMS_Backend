package org.saima.LMS.model;


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
@Table(name = "t_RecordClass")
public class RecordClass {
	
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;

@ManyToOne
@JoinColumn(name = "course_id", nullable = false)
private Course course;

private String videoUrl;


public RecordClass(Course course,String videoUrl) {
	this.course = course;
	this.videoUrl = videoUrl;
}
}