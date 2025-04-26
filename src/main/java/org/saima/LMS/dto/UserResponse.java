package org.saima.LMS.dto;

import java.time.LocalDateTime;

import org.saima.LMS.constants.Role;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserResponse {
	private Long id;
	private String email;
	private Role role;
	private String firstName;
	private String lastName;
	private String phoneNumber;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
}