package org.saima.LMS.dto;

import java.util.Set;

import org.saima.LMS.constants.Role;
import org.saima.LMS.model.Course;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserResponse {

	private Long id;
	private String email;
	private Role role;
	private String name;
	private String fatherName;
	private String motherName;
	private String phone;
	private String address;
	private String avatarUrl;
	private String bio;
	private String courseName;
	
//	public void setCourseName(Set<Course> courses) {
//		
//	}

}