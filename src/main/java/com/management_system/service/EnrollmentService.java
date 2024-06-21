package com.management_system.service;

import com.management_system.model.Course;
import com.management_system.model.Enrollment;
import com.management_system.model.Student;
import com.management_system.repository.EnrollmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EnrollmentService {
    private final EnrollmentRepository enrollmentRepository;

    public void saveEnrollment(Enrollment enrollment) {
        enrollmentRepository.save(enrollment);
    }

    public void saveAllEnrollments(List<Enrollment> enrollments) {
        enrollmentRepository.saveAll(enrollments);
    }

    public Boolean isEmpty() {
        return enrollmentRepository.count() == 0;
    }

    public List<Enrollment> getCompletedEnrollmentsByStudent(long studentId) {
        return enrollmentRepository.findAllByCompletedAndStudentIdOrderByUpdatedOnDesc(true, studentId);
    }

    public List<Enrollment> getNotCompletedEnrollmentsByStudent(long studentId) {
        return enrollmentRepository.findAllByCompletedAndStudentIdOrderByUpdatedOnDesc(false, studentId);
    }

    public List<Long> getAllCourseIdsByStudentId(long studentId) {
        return enrollmentRepository.findAllCourseIdsByStudentId(studentId);
    }

    public Enrollment getEnrollmentByStudentAndCourse(long studentId, long courseId) {
        return enrollmentRepository.findByStudentIdAndCourseId(studentId, courseId).orElse(null);
    }

    public List<Enrollment> getEnrollmentsByStudentId(long studentId) {
        return enrollmentRepository.findAllByStudentId(studentId);
    }

    public void removeEnrollment(Enrollment enrollment) {
        enrollmentRepository.delete(enrollment);
    }

    public void addEnrollment(Student student, Course course) {
        Enrollment enrollment = new Enrollment("N/A", false, null, student, course);
        saveEnrollment(enrollment);
    }

    public Enrollment completeEnrollment(long studentId, long courseId, String grade) {
        Enrollment enrollment = getEnrollmentByStudentAndCourse(studentId, courseId);

        if (enrollment != null) {
            enrollment.setCompleted(true);
            enrollment.setCompletedDate(LocalDate.now());
            enrollment.setGrade(grade);
            enrollmentRepository.save(enrollment);
            return enrollment;
        }
        return null;
    }

    public Course deleteEnrollment(long studentId, long courseId) {
        Enrollment enrollment = getEnrollmentByStudentAndCourse(studentId, courseId);

        if (enrollment != null) {
            removeEnrollment(enrollment);
            return enrollment.getCourse();
        }
        return null;
    }
}
