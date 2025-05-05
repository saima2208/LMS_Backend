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
public class LessonDTO {
	@NotNull(message = "Course ID cannot be null")
    private Long courseId;

    @NotNull(message = "Lesson topic cannot be null")
    private String topic;

    private String content;
}