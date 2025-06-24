package org.saima.LMS.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "t_marks")
public class Marks {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY) // Many Marks can belong to one Course
    @JoinColumn(name = "course_id", nullable = false) // Foreign key to Course
    private Course course;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY) // Many Marks can belong to one Assignment
    @JoinColumn(name = "assignment_id", nullable = false) // Foreign key to Assignment
    private Assignment assignment;
    
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY) // Many Marks can belong to one Student
//    @JoinColumn(name = "student_id", nullable = false) // Foreign key to User (student)
    @JoinColumns({
        @JoinColumn(name = "student_id", referencedColumnName = "id", nullable = false),
        @JoinColumn(name = "student_name", referencedColumnName = "name", nullable = false)
    })
    private User student;

    private Integer marks;
    private Integer totalMarks;

    public Marks(Course course, Assignment assignment,User student, Integer marks,Integer totalMarks) {
        this.course = course;
        this.assignment = assignment;
        this.student = student;
        this.marks = marks;
        this.totalMarks = totalMarks;
    }
}
