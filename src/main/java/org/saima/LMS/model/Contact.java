package org.saima.LMS.model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name ="t_contact")

public class Contact {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String email;
	private String message;
	private LocalDateTime createdAt;

	public Contact(String name, String email, String message) {
		this.name = name;
		this.email = email;
		this.message = message;

	}

	@PrePersist
	protected void onCreate() {
		createdAt = LocalDateTime.now();
	}
}
