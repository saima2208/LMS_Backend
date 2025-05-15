package org.saima.LMS.dto;

import java.time.LocalDateTime;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FeedbackDTO {
   
    private Long studentId; 
    
    @Size(max = 500, message = "Content must not exceed 500 characters")
    private String content; // Feedback content
    
    private Integer rating; // Numeric rating (e.g., 1-5)
    
    private String stars; // Star representation of the rating
    
    private LocalDateTime createdAt;
    
    private LocalDateTime updatedAt;


 }

