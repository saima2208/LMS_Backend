package org.saima.LMS.controller;

import java.util.List;

import org.saima.LMS.dto.FeedbackDTO;
import org.saima.LMS.dto.FeedbackWithUserNameDTO;
import org.saima.LMS.repository.FeedbackRepository;
import org.saima.LMS.repository.UserRepository;
import org.saima.LMS.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/feedbacks")
public class FeedbackController {

	@Autowired
	private FeedbackService feedbackService;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private FeedbackRepository feedbackRepository;

	@PostMapping
	public ResponseEntity<FeedbackDTO> addFeedback(@RequestBody FeedbackDTO feedbackDTO) {
		FeedbackDTO createdFeedback = feedbackService.addFeedback(feedbackDTO);
		return ResponseEntity.ok(createdFeedback);
	}
	
//	@PostMapping
//	public ResponseEntity<FeedbackWithUserNameDTO> addFeedback(@RequestBody FeedbackWithUserNameDTO feedbackDTO) {
//		FeedbackDTO createdFeedback = feedbackService.addFeedback(feedbackDTO);
//		return ResponseEntity.ok(createdFeedback);
//	}

	@GetMapping
	public ResponseEntity<List<FeedbackDTO>> getAllFeedbacks() {
		return ResponseEntity.ok(feedbackService.getAllFeedbacks());
	}

	@GetMapping("/withName")
	public ResponseEntity<List<FeedbackWithUserNameDTO>> getAllFeedbacksWithUserName() {
		return ResponseEntity.ok(feedbackService.getAllFeedbacksWithUserName());
	}

	@GetMapping("/{id}")
	public ResponseEntity<FeedbackDTO> getFeedbackById(@PathVariable Long id) {
		return ResponseEntity.ok(feedbackService.getFeedbackById(id));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteFeedback(@PathVariable Long id) {
		feedbackService.deleteFeedback(id);
		return ResponseEntity.noContent().build();
	}
}
