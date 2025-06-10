package org.saima.LMS.dto;

import org.saima.LMS.constants.Role;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class UserResponseDto {


    private Long id;
    private String email;
    private Role role;
    private String name;
    private String fatherName;
    private String motherName;
  
    private String phone;
//    private String gender;
//    private String nationality;
    private String address;
    private String avatarUrl;
    private String bio;

}