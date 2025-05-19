package org.saima.LMS.controller;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.saima.LMS.dto.FeedbackDTO;
import org.saima.LMS.model.Feedback;
import org.saima.LMS.model.User;
import org.saima.LMS.repository.FeedbackRepository;
import org.saima.LMS.repository.UserRepository;
import org.saima.LMS.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    
   
   

    @GetMapping
    public ResponseEntity<List<FeedbackDTO>> getAllFeedbacks() {
        return ResponseEntity.ok(feedbackService.getAllFeedbacks());
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

