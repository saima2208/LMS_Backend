package org.saima.LMS.dto;

import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SubmitAssignmentDTO {


	@NotNull(message = "student ID cannot be null")
    private Long studentId;

    @NotNull(message = "assginment id cannot be null")
    private Long assginmentId;

  private MultipartFile file;
    
    
}
