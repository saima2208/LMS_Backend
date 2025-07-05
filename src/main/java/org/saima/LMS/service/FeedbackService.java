package org.saima.LMS.service;

import java.util.List;
import java.util.stream.Collectors;

import org.saima.LMS.dto.FeedbackDTO;
import org.saima.LMS.dto.FeedbackWithUserNameDTO;
import org.saima.LMS.model.Feedback;
import org.saima.LMS.model.User;
import org.saima.LMS.repository.FeedbackRepository;
import org.saima.LMS.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FeedbackService {

	@Autowired
	private FeedbackRepository feedbackRepository;

	@Autowired
	private UserRepository userRepository;

//    private FeedbackDTO mapToDTO(Feedback feedback) {
//		return new FeedbackDTO(feedback.getId(), feedback.getStudent().getName(), feedback.getContent(),
//				feedback.getRating(), feedback.getCreatedAt(), feedback.getUpdatedAt()
//			);
//	}

	public FeedbackDTO addFeedback(FeedbackDTO feedbackDTO) {
		User student = userRepository.findById(feedbackDTO.getStudentId())
				.orElseThrow(() -> new RuntimeException("Student not found with ID: " + feedbackDTO.getStudentId()));

		Feedback feedback = new Feedback(student, feedbackDTO.getContent(), feedbackDTO.getRating());
		Feedback savedFeedback = feedbackRepository.save(feedback);

		return convertToDTO(savedFeedback);
	}
	

	public List<FeedbackDTO> getAllFeedbacks() {
		return feedbackRepository.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
	}

	public FeedbackDTO getFeedbackById(Long id) {
		Feedback feedback = feedbackRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Feedback not found with ID: " + id));

		return convertToDTO(feedback);
	}

	public void deleteFeedback(Long id) {
		feedbackRepository.deleteById(id);
	}

	private FeedbackDTO convertToDTO(Feedback feedback) {
		FeedbackDTO feedbackDTO = new FeedbackDTO();

		feedbackDTO.setStudentId(feedback.getStudent().getId());
		feedbackDTO.setContent(feedback.getContent());
		feedbackDTO.setRating(feedback.getRating());
		feedbackDTO.setStars(feedback.getStars());
		feedbackDTO.setCreatedAt(feedback.getCreatedAt());
		feedbackDTO.setUpdatedAt(feedback.getUpdatedAt());
		return feedbackDTO;
	}

	public List<FeedbackWithUserNameDTO> getAllFeedbacksWithUserName() {
		return feedbackRepository.fetchFeedbackWithStudentInfo();
	}

}
