package org.saima.LMS.service;

import java.util.List;
import java.util.Optional;

import org.saima.LMS.dto.MarksDTO;
import org.saima.LMS.model.Assignment;
import org.saima.LMS.model.Course;
import org.saima.LMS.model.Marks;
import org.saima.LMS.model.User;
import org.saima.LMS.repository.AssignmentRepository;
import org.saima.LMS.repository.CourseRepository;
import org.saima.LMS.repository.MarksRepository;
import org.saima.LMS.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MarksService {

	@Autowired
    private MarksRepository marksRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private AssignmentRepository assignmentRepository;

    @Autowired
    private UserRepository userRepository;

    public List<Marks> getAllMarks() {
        return marksRepository.findAll();
    }

    public Marks addMarks(MarksDTO marksDTO) {
        Optional<Course> courseOpt = courseRepository.findById(marksDTO.getCourseId());
        Optional<Assignment> assignmentOpt = assignmentRepository.findById(marksDTO.getAssignmentId());
        Optional<User> studentOpt = userRepository.findById(marksDTO.getStudentId());

        if (courseOpt.isPresent() && assignmentOpt.isPresent() && studentOpt.isPresent()) {
            Marks marks = new Marks();
            marks.setCourse(courseOpt.get());
            marks.setAssignment(assignmentOpt.get());
            marks.setStudent(studentOpt.get());
            marks.setMarks(marksDTO.getMarks());
            return marksRepository.save(marks);
        } else {
            throw new IllegalArgumentException("Invalid course, assignment, or student ID");
        }
    }

    public void deleteMarks(Long id) {
        marksRepository.deleteById(id);
    }
    
    public Marks updateMarks(Long id, MarksDTO marksDTO) {
        Optional<Marks> existingMarksOpt = marksRepository.findById(id);
        if (existingMarksOpt.isPresent()) {
            Marks existingMarks = existingMarksOpt.get();

            Optional<Course> courseOpt = courseRepository.findById(marksDTO.getCourseId());
            Optional<Assignment> assignmentOpt = assignmentRepository.findById(marksDTO.getAssignmentId());
            Optional<User> studentOpt = userRepository.findById(marksDTO.getStudentId());

            if (courseOpt.isPresent() && assignmentOpt.isPresent() && studentOpt.isPresent()) {
                existingMarks.setCourse(courseOpt.get());
                existingMarks.setAssignment(assignmentOpt.get());
                existingMarks.setStudent(studentOpt.get());
                existingMarks.setMarks(marksDTO.getMarks());

                return marksRepository.save(existingMarks);
            } else {
                throw new IllegalArgumentException("Invalid course, assignment, or student ID");
            }
        } else {
            throw new IllegalArgumentException("Marks entry with ID " + id + " not found");
        }
    }


}

