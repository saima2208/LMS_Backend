package org.saima.LMS.dto;

import java.time.LocalDateTime;

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
    
    private Long studentId;
    
    private Long courseId;
    
    private LocalDateTime enrollmentDate;
    
    private String paymentMethod;

    public EnrollmentDTO(Long studentId, Long courseId, LocalDateTime enrollmentDate, String paymentMethod) {
        this.studentId = studentId;
        this.courseId = courseId;
        this.enrollmentDate = enrollmentDate;
        this.paymentMethod = paymentMethod;
    }
}
