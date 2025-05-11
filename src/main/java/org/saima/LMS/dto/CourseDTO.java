package org.saima.LMS.dto;

import java.time.LocalDate;

import org.saima.LMS.annotation.UniqueCourseName;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class CourseDTO {

//	@NotNull(message = "Teacher cannot be null")
	private Long teacherId;

//	private List<Long> lessonIds;

	@NotNull(message = "Course title cannot be null")
	// @UniqueCourseName // Custom unique validation
	private String courseName;

	private String price;
	private LocalDate startDate;
	private String duration;
	private String description;
	
	private String image;
	




}
