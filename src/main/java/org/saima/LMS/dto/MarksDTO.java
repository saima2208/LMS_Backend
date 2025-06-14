package org.saima.LMS.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MarksDTO {
	
private Long courseId;
private Long assignmentId;
private Long studentId;
private String StudentName;
private Integer marks;

}
