package org.saima.LMS.dto;

import org.saima.LMS.constants.Role;

import jakarta.validation.constraints.Email;

public record UserUpdateRequest(@Email(message = "Email should be valid") String email,

		Role role, String name,String fatherName, String motherName, String phone) {
}