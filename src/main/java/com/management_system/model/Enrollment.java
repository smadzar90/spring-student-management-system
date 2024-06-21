package com.management_system.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "enrollments")
public class Enrollment implements Comparable<Enrollment> {

    public Enrollment(String grade, Boolean completed, LocalDate completedDate, Student student, Course course) {
        this.grade = grade;
        this.completed = completed;
        this.completedDate = completedDate;
        this.student = student;
        this.course = course;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String grade;
    private Boolean completed;
    private LocalDate completedDate;

    @CreationTimestamp
    private LocalDateTime createdOn;

    @UpdateTimestamp
    private LocalDateTime updatedOn;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

    @Override
    public String toString() {
        return "Enrollment{" +
                "id=" + id +
                ", student='" + student + '\'' +
                ", course='" + course + '\'' +
                ", grade='" + grade + '\'' +
                ", completed='" + completed + '\'' +
                ", student='" + student + '\'' +
                '}';
    }

    @Override
    public int compareTo(Enrollment other) {
        return this.getUpdatedOn().compareTo(other.updatedOn);
    }
}
