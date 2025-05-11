//package org.saima.LMS.dto;
//
//import java.time.LocalDate;
//
//import org.saima.LMS.constants.EnrollmentStatus;
//
//
//import jakarta.validation.constraints.NotNull;
//import lombok.AllArgsConstructor;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;
//
//@Getter
//@Setter
//@NoArgsConstructor
//@AllArgsConstructor
//public class EnrollmentDTO {
//
//	private Long id;
//
//	@NotNull(message = "Course name cannot be null")
//	private String courseName;
//	
//	private String name;
//	
//	@NotNull(message = "Email cannot be null")
//	private String email;
//	
//	private String phone;
//
//	private LocalDate enrollmentDate;
//
//	private String paymentMethod;
//	
//   
//    private EnrollmentStatus status;
//
//
//}


package org.saima.LMS.dto;

import java.time.LocalDate;

import org.saima.LMS.constants.EnrollmentStatus;

import jakarta.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EnrollmentDTO {
    private Long id;

    @NotNull(message = "Course name cannot be null")
    private String courseName;

    private String name;

    @NotNull(message = "Email cannot be null")
    private String email;

    private String phone;

    private LocalDate enrollmentDate;

    private String paymentMethod;

    private EnrollmentStatus status;
}

