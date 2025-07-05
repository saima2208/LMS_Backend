package org.saima.LMS.dto;

import java.time.LocalDateTime;

public class FeedbackWithUserNameDTO {
	private Long id;
	private String content;
	private Integer rating;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
	private String studentName;
	private String studentAvatarUrl;

	public FeedbackWithUserNameDTO(Long id, String content, Integer rating, LocalDateTime createdAt,
			LocalDateTime updatedAt, String studentName, String studentAvatarUrl) {
		this.id = id;
		this.content = content;
		this.rating = rating;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.studentName = studentName;
		this.studentAvatarUrl = studentAvatarUrl;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getRating() {
		return rating;
	}

	public void setRating(Integer rating) {
		this.rating = rating;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public String getStudentAvatarUrl() {
		return studentAvatarUrl;
	}

	public void setStudentAvatarUrl(String studentAvatarUrl) {
		this.studentAvatarUrl = studentAvatarUrl;
	}
	
	
}
