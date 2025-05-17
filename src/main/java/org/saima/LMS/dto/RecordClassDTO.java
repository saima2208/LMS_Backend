package org.saima.LMS.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RecordClassDTO {

	@NotNull(message = "Course name cannot be null")
    private String courseName;

	@NotNull(message = "lesson ID cannot be null")
    private Long lessonId;
	
    @NotNull(message = "Lesson topic cannot be null")
    private String videoUrl;
}
