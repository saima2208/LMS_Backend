package org.saima.LMS.controller;

import java.io.IOException;
import java.security.GeneralSecurityException;

import org.saima.LMS.model.EmailRequest;
import org.saima.LMS.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import jakarta.mail.MessagingException;

@RestController
public class EmailController {
	@Autowired
	private EmailService emailService;

	@PostMapping("/api/send-email")
	public ResponseEntity<String> sendEmail(@RequestBody EmailRequest request) {
		try {
			emailService.sendEmail(request.getTo(), request.getSubject(), request.getBody());
			return ResponseEntity.ok("Email sent successfully");
		} catch (MessagingException | IOException | GeneralSecurityException e) {
			return ResponseEntity.internalServerError().body("Failed to send email: " + e.getMessage());
		}
	}
}
