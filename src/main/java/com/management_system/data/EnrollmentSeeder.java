package com.management_system.data;

import com.management_system.model.Enrollment;
import com.management_system.service.CourseService;
import com.management_system.service.EnrollmentService;
import com.management_system.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class EnrollmentSeeder {
    private final StudentService studentService;
    private final CourseService courseService;
    private final EnrollmentService enrollmentService;

    /**
     * Seed the data to enrollment table only if StudentSystemDB is empty
     * Default data will be seeded
     */
    public void setUp() {
        List<Enrollment> enrollments = new ArrayList<>();
        enrollments.add(new Enrollment("A", true, LocalDate.now(), studentService.getStudentById(1), courseService.getCourseById(8)));
        enrollments.add(new Enrollment("A", true, LocalDate.now(), studentService.getStudentById(1), courseService.getCourseById(1)));
        enrollments.add(new Enrollment("C", true, LocalDate.now(), studentService.getStudentById(3), courseService.getCourseById(3)));
        enrollments.add(new Enrollment("A", true, LocalDate.now(), studentService.getStudentById(4), courseService.getCourseById(4)));
        enrollments.add(new Enrollment("B", true, LocalDate.now(), studentService.getStudentById(4), courseService.getCourseById(5)));
        enrollments.add(new Enrollment("C", true, LocalDate.now(), studentService.getStudentById(6), courseService.getCourseById(6)));
        enrollments.add(new Enrollment("A", true, LocalDate.now(), studentService.getStudentById(7), courseService.getCourseById(7)));
        enrollments.add(new Enrollment("B", true, LocalDate.now(), studentService.getStudentById(8), courseService.getCourseById(8)));
        enrollments.add(new Enrollment("C", true, LocalDate.now(), studentService.getStudentById(9), courseService.getCourseById(9)));
        enrollments.add(new Enrollment("A", true, LocalDate.now(), studentService.getStudentById(10), courseService.getCourseById(10)));
        enrollments.add(new Enrollment("B", true, LocalDate.now(), studentService.getStudentById(11), courseService.getCourseById(11)));
        enrollments.add(new Enrollment("C", true, LocalDate.now(), studentService.getStudentById(12), courseService.getCourseById(12)));
        enrollments.add(new Enrollment("A", true, LocalDate.now(), studentService.getStudentById(13), courseService.getCourseById(13)));
        enrollments.add(new Enrollment("B", true, LocalDate.now(), studentService.getStudentById(14), courseService.getCourseById(14)));
        enrollments.add(new Enrollment("N/A", false, null, studentService.getStudentById(15), courseService.getCourseById(1)));
        enrollments.add(new Enrollment("N/A", false, null, studentService.getStudentById(12), courseService.getCourseById(3)));
        enrollments.add(new Enrollment("N/A", false, null, studentService.getStudentById(18), courseService.getCourseById(4)));
        enrollments.add(new Enrollment("N/A", false, null, studentService.getStudentById(12), courseService.getCourseById(5)));
        enrollments.add(new Enrollment("N/A", false, null, studentService.getStudentById(20), courseService.getCourseById(6)));
        enrollments.add(new Enrollment("N/A", false, null, studentService.getStudentById(21), courseService.getCourseById(7)));
        enrollments.add(new Enrollment("N/A", false, null, studentService.getStudentById(22), courseService.getCourseById(8)));
        enrollments.add(new Enrollment("N/A", false, null, studentService.getStudentById(23), courseService.getCourseById(9)));
        enrollments.add(new Enrollment("N/A", false, null, studentService.getStudentById(24), courseService.getCourseById(10)));
        enrollments.add(new Enrollment("N/A", false, null, studentService.getStudentById(25), courseService.getCourseById(11)));
        enrollments.add(new Enrollment("N/A", false, null, studentService.getStudentById(28), courseService.getCourseById(12)));
        enrollments.add(new Enrollment("N/A", false, null, studentService.getStudentById(27), courseService.getCourseById(13)));
        enrollments.add(new Enrollment("N/A", false, null, studentService.getStudentById(28), courseService.getCourseById(14)));

        enrollmentService.saveAllEnrollments(enrollments);
        System.out.println("Enrollment records seeded to database!");
        studentService.updateGPAForAllStudents();
        courseService.updateStatsForAllCourses();
    }
}
