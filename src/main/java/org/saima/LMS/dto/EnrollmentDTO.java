package org.saima.LMS.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EnrollmentDTO {

	private Long enrollmentId;

	private Long courseId;

	private LocalDate enrollmentDate;

	private String paymentMethod;

	public EnrollmentDTO(Long courseId, LocalDate enrollmentDate, String paymentMethod) {
		this.courseId = courseId;
		this.enrollmentDate = enrollmentDate;
		this.paymentMethod = paymentMethod;
	}
}
