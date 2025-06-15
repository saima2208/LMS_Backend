package org.saima.LMS.dto;

import org.saima.LMS.constants.Role;
import org.saima.LMS.model.Course;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserResponse {
//    private Long id;
//    private String email;
//    private Role role;
//    private String name;
//    private String fatherName;
//    private String motherName;
//    private String phone;
//    private String avatarUrl;

	private Long id;
	private String email;
	private Role role;
	private String name;
	private String fatherName;
	private String motherName;

	private String phone;
//	    private String gender;
//	    private String nationality;
	private String address;
	private String avatarUrl;
	private String bio;
	private Course courseName;

	// Getters and Setters

}