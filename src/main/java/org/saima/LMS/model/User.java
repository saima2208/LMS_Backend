package org.saima.LMS.model;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.*;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.saima.LMS.constants.Role;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "T_USER")
//public class User {
//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	private Long id;
//
//	@Column(unique = true, nullable = false)
//	private String email;
//
//	@JsonIgnore
//	@Column(nullable = false)
//	private String password;
//
//	@Enumerated(EnumType.STRING)
//	@Column(nullable = false)
//	private Role role;
//
//	private String name;
//	private String phoneNumber;
//
//	@Column(name = "created_at")
//	private LocalDateTime createdAt;
//
//	@Column(name = "updated_at")
//	private LocalDateTime updatedAt;
//
//	public User(String email, String password, Role role, String name, String phoneNumber) {
//		this.email = email;
//		this.password = password;
//		this.role = role;
//		this.name = name;
//		this.phoneNumber = phoneNumber;
//
//	}
//
//	@PrePersist
//	protected void onCreate() {
//		createdAt = LocalDateTime.now();
//		updatedAt = LocalDateTime.now();
//	}
//
//	@PreUpdate
//	protected void onUpdate() {
//		updatedAt = LocalDateTime.now();
//	}
//}

public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long user_Id;

	private String name;
	private String fatherName;
	private String motherName;

	@Column(unique = true)
	private String email;

	private String password;
	private String phone;
	private String avatarUrl;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private Role role;


	public User( String name,String email, String password, Role role,String fatherName,String motherName, String phone) {
		this.name = name;
		this.email = email;
		this.password = password;
		this.role = role;
		this.fatherName = fatherName;
		this.motherName = motherName;
		this.phone = phone;

	}

}
