package org.saima.LMS.model;

import java.util.HashSet;
import java.util.Set;

import org.saima.LMS.constants.Role;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "T_USERS")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(unique = true, nullable = false)
	private String email;

	@JsonIgnore
	@Column(nullable = false)
	private String password;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private Role role;

	private String name;
	private String fatherName;
	private String motherName;
	private String phone;
	private String address;
	private String avatarUrl;
	private String bio;

	@ManyToMany
	@JoinTable(name = "map_user_courses",
			joinColumns = @JoinColumn(name = "user_id"), // Foreign key column for User
			inverseJoinColumns = @JoinColumn(name = "course_id") // Foreign key column for Course
	)
	private Set<Course> courses = new HashSet<>();

	public User(String email, String password, Role role, String name, String fatherName, String motherName,
			String phone, String address, String avatarUrl,String bio) {
		this.email = email;
		this.password = password;
		this.role = role;
		this.name = name;
		this.fatherName = fatherName;
		this.motherName = motherName;
		this.phone = phone;
		this.address = address;
		this.avatarUrl = avatarUrl;
		this.bio = bio;
	}

//    public String getAvatarUrl() {
//        if (avatarUrl != null) {
//            return "data:image/jpeg;base64," + java.util.Base64.getEncoder().encodeToString(avatarUrl);
//        }
//        return null;
//    }
}