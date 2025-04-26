package org.saima.LMS.dto;

import jakarta.validation.constraints.NotBlank;

public record LoginRequest(@NotBlank(message = "Email cannot be blank") String email,

		@NotBlank(message = "Password cannot be blank") String password) {
}