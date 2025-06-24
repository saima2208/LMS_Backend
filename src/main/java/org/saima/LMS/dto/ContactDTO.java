package org.saima.LMS.dto;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ContactDTO {
	
@NotNull(message ="Name can not be null")	
private String name;
@NotNull(message ="email can not be null")	
private String email;
private String message;
private LocalDateTime createdAt;


}