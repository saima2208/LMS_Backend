
package org.saima.LMS.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.saima.LMS.validator.UniqueCourseNameValidator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Documented
@Constraint(validatedBy = UniqueCourseNameValidator.class)
@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface UniqueCourseName {

	String message() default "Course name must be unique";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}
