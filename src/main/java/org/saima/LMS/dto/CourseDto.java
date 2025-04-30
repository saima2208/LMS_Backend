package org.saima.LMS.dto;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CourseDto {
    @NotNull (message = "teacher id can not be null")
    private Long teacherId; // Only teacher's ID, not full object
    private List<Long> lessonIds; // Only lesson IDs, not full objects
    private String name;
    private String price;
    private LocalDateTime startDate;
    private Long duration;
    private String description;
}
