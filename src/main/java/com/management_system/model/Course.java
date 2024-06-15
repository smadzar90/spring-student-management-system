package com.management_system.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name = "courses")

//Owner side for Course-Mentor relationship
public class Course {

    public Course(String name, String description, String courseCode, int studentsEnrolled, int studentsCompleted, Mentor mentor) {
        this.name = name;
        this.description = description;
        this.courseCode = courseCode;
        this.studentsEnrolled = studentsEnrolled;
        this.studentsCompleted = studentsCompleted;
        this.mentor = mentor;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private String courseCode;

    private Integer studentsEnrolled;
    private Integer studentsCompleted;

    @CreationTimestamp
    private LocalDateTime createdOn;

    @UpdateTimestamp
    private LocalDateTime updatedOn;

    @ManyToOne
    @JoinColumn(name="mentor_id")
    private Mentor mentor;

    @OneToMany(mappedBy = "course")
    private List<Enrollment> enrollments = new ArrayList<>();

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description= " + description +
                ", course_code= " + courseCode +
                ", students_enrolled= " + studentsEnrolled +
                ", students_completed= " + studentsCompleted +
                ", created_on= " + createdOn +
                ", updated_on= " + updatedOn +
                ", mentor= " + mentor +
                '}';
    }
}
