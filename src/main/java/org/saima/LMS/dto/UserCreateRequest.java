package org.saima.LMS.dto;



import org.saima.LMS.constants.Role;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UserCreateRequest(
//        @NotBlank(message = "Username cannot be blank")
//        @Size(min = 3, max = 20, message = "Username must be between 3 and 20 characters")
//        String username,

        @NotBlank(message = "Email cannot be blank")
        @Email(message = "Email should be valid")
        String email,

        @NotBlank(message = "Password cannot be blank")
        @Size(min = 6, message = "Password must be at least 6 characters")
        String password,

        @NotNull(message = "Role cannot be null")
        Role role,

        String name,
        String fatherName,
        String motherName,
        String phone,
        String avatarUrl,
        String address,
      
        String nationality,
        String bio
      
) {
}