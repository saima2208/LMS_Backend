package org.saima.LMS.validator;

import org.saima.LMS.annotation.UniqueCourseName;
import org.saima.LMS.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

@Component
public class UniqueCourseNameValidator implements ConstraintValidator<UniqueCourseName, String> {

	@Autowired
	private CourseRepository courseRepository;

	@Override
	public boolean isValid(String courseName, ConstraintValidatorContext context) {
		if (courseName == null || courseName.trim().isEmpty()) {
			return true; // @NotNull should handle this separately
		}

		return !courseRepository.existsByName(courseName);
	}
}