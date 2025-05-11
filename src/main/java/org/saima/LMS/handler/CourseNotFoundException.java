package org.saima.LMS.handler;



public class CourseNotFoundException extends RuntimeException {
    public CourseNotFoundException(String message) {
        super(message);
    }
}
