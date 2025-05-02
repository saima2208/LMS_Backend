package org.saima.LMS.dto;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EnrollmentDTO {
	@NotNull(message = "Course ID cannot be null")
	private Long courseId;
	
    private Long studentId;
    
    private LocalDateTime enrolledDate;
    
    private String paymentMethod;
    
    private String status;
}
