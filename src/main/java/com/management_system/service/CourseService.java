package com.management_system.service;

import com.management_system.model.Course;
import com.management_system.model.Enrollment;
import com.management_system.repository.CourseRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseService {

    private final CourseRepository courseRepository;
    private final EnrollmentService enrollmentService;

    public Boolean isEmpty() {
        return courseRepository.count() == 0;
    }

    public void saveAllCourses(List<Course> courses) {
        courseRepository.saveAll(courses);
    }

    public Course getCourseById(long id) {
        return courseRepository.findById(id).orElse(null);
    }

    public Course getCourseByStringId(String id) {
        try {
            long studentId = Long.parseLong(id);
            return courseRepository.findById(studentId).orElse(null);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    public List<Course> getAllCoursesByIdAsc() {
        return courseRepository.findAllByOrderByIdAsc();
    }

    public void saveCourse(Course course) {
        courseRepository.save(course);
    }

    public void updateCourseStatsOnCompletion(Course course) {
        course.setStudentsCompleted(course.getStudentsCompleted() + 1);
        course.setStudentsEnrolled(course.getStudentsEnrolled() - 1);
        saveCourse(course);
    }

    public void updateCourseStatsOnAddition(Course course) {
        course.setStudentsEnrolled(course.getStudentsEnrolled() + 1);
        saveCourse(course);
    }

    public void updateCourseStatsOnDeletion(Course course) {
        course.setStudentsEnrolled(course.getStudentsEnrolled() - 1);
        saveCourse(course);
    }

    @Transactional
    public void updateCoursesStatsByStudentDeleted(long studentId) {
        List<Enrollment> enrollments = enrollmentService.getEnrollmentsByStudentId(studentId);

        for (Enrollment enrollment : enrollments) {
            if (enrollment.getCompleted()) {
                int completed = enrollment.getCourse().getStudentsCompleted();
                enrollment.getCourse().setStudentsCompleted(completed - 1);
            } else {
                int enrolled = enrollment.getCourse().getStudentsEnrolled();
                enrollment.getCourse().setStudentsEnrolled(enrolled - 1);
            }
        }
    }

    @Transactional
    public void updateStatsForAllCourses() {
        List<Course> courses = courseRepository.findAll();
        for (Course course : courses) {
            int completed = 0;
            int enrolled = 0;
            List<Enrollment> enrollments = course.getEnrollments();
            if (!enrollments.isEmpty()) {
                for (Enrollment enrollment : enrollments) {
                    if (enrollment.getCompleted()) {
                        completed++;
                    } else {
                        enrolled++;
                    }
                }
            }
            course.setStudentsCompleted(completed);
            course.setStudentsEnrolled(enrolled);
            courseRepository.save(course);
        }
    }
}
