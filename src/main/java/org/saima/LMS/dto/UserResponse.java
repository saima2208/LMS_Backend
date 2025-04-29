package org.saima.LMS.dto;

import java.time.LocalDateTime;

import org.saima.LMS.constants.Role;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserResponse {
	private Long user_id;
	private String name;
	private String email;
	private Role role;

	private String fatherName;
	private String motherName;

	private String phone;
//	private LocalDateTime createdAt;
//	private LocalDateTime updatedAt;
}