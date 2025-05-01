package org.saima.LMS.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

import org.saima.LMS.annotation.UniqueCourseName;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CourseDTO {

    @NotNull(message = "Teacher cannot be null")
    private Long teacherId;

    private List<Long> lessonIds;

    @NotNull(message = "Course title cannot be null")
    @UniqueCourseName // Custom unique validation
    private String name;

    private String price;
    private LocalDateTime startDate;
    private String duration;
    private String description;

    // Getters and Setters
}
