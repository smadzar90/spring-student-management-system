package com.management_system.service;

import com.management_system.model.Course;
import com.management_system.model.Enrollment;
import com.management_system.model.Student;
import com.management_system.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentService {
    private final StudentRepository studentRepository;
    private final EnrollmentService enrollmentService;
    private final CourseService courseService;

    public Boolean isEmpty() {
        return studentRepository.count() == 0;
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public List<Student> getAllStudentsByGPA() {
        return studentRepository.findAllByOrderByCumulativeGpaAsc();
    }

    public List<Student> getAllStudentsByGPADesc() {
        return studentRepository.findAllByOrderByCumulativeGpaDesc();
    }

    public List<Student> getAllStudentsByID() {
        return studentRepository.findAllByOrderByIdAsc();
    }

    public List<Student> getAllStudentsByIDDesc() {
        return studentRepository.findAllByOrderByIdDesc();
    }

    public List<Student> getAllStudentsByDOB() {
        return studentRepository.findAllByOrderByDateOfBirthAsc();
    }

    public List<Student> getAllStudentsByDOBDesc() {
        return studentRepository.findAllByOrderByDateOfBirthDesc();
    }

    public List<Student> getAllStudentsByUpdatedOnDesc() {
        return studentRepository.findAllByOrderByUpdatedOnDesc();
    }

    public void saveStudent(Student student) {
        studentRepository.save(student);
    }

    public void saveAllStudents(List<Student> students) {
        studentRepository.saveAll(students);
    }

    public Student getStudentById(long id) {
        return studentRepository.findById(id).orElse(null);
    }

    public Student getStudentByStringId(String id) {
        try {
            long studentId = Long.parseLong(id);
            return studentRepository.findById(studentId).orElse(null);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    public void deleteStudentById(long id) {
        studentRepository.deleteById(id);
    }

    public void addEnrollment(long studentId, long courseId) {
        Student student = getStudentById(studentId);
        Course course = courseService.getCourseById(courseId);

        if (student != null && course != null) {
            enrollmentService.addEnrollment(student, course);
            courseService.updateCourseStatsOnAddition(course);
        }
    }

    public void completeEnrollment(long studentId, long courseId, String grade) {
        Enrollment enrollment = enrollmentService.completeEnrollment(studentId, courseId, grade);

        if (enrollment != null) {
            courseService.updateCourseStatsOnCompletion(enrollment.getCourse());
            updateGPAForStudent(enrollment.getStudent());
        }
    }

    public void deleteEnrollment(long studentId, long courseId) {
        Course course = enrollmentService.deleteEnrollment(studentId, courseId);

        if (course != null) {
            courseService.updateCourseStatsOnDeletion(course);
        }
    }

    public List<Course> getRegisteredCoursesByStudent(Student student) {
        List<Course> registeredCourses = new ArrayList<>();

        for (Enrollment enrollment : student.getEnrollments()) {
            if (!enrollment.getCompleted()) {
                registeredCourses.add(enrollment.getCourse());
            }
        }

        registeredCourses.sort((c1, c2) -> c2.getUpdatedOn().compareTo(c1.getUpdatedOn()));
        return registeredCourses;
    }

    public List<Course> getUnregisteredCoursesByStudent(Student student) {
        List<Course> courses = courseService.getAllCoursesByIdAsc();
        List<Long> registeredCourses = enrollmentService.getAllCourseIdsByStudentId(student.getId());

        courses.removeIf(course -> registeredCourses.contains(course.getId()));
        return courses;
    }

    public void updateStudent(Student student) {
        Student toUpdateStudent = studentRepository.findById(student.getId()).orElse(null);

        if (toUpdateStudent != null) {
            toUpdateStudent.setFirstName(student.getFirstName());
            toUpdateStudent.setLastName(student.getLastName());
            toUpdateStudent.setGender(student.getGender());
            toUpdateStudent.setEmail(student.getEmail());
            toUpdateStudent.setAddress(student.getAddress());
            toUpdateStudent.setState(student.getState());
            toUpdateStudent.setZipcode(student.getZipcode());
            toUpdateStudent.setPhoneNumber(student.getPhoneNumber());
            toUpdateStudent.setDateOfBirth(student.getDateOfBirth());
            studentRepository.save(toUpdateStudent);
        }
    }

    public void updateGPAForStudent(Student student) {
        List<Enrollment> enrollmentsByStudent = enrollmentService.getCompletedEnrollmentsByStudent(student.getId());

        if (!enrollmentsByStudent.isEmpty()) {
            double gpa = 0.0;
            for (Enrollment enrollment : enrollmentsByStudent) {
                gpa += this.getGradeValue(enrollment.getGrade());
            }

            gpa /= enrollmentsByStudent.size();
            student.setCumulativeGpa(gpa);
            studentRepository.save(student);
        }
    }

    public void updateGPAForAllStudents() {
        List<Student> students = studentRepository.findAll();

        for (Student student : students) {
            updateGPAForStudent(student);
        }
    }

    public Double getGradeValue(String grade) {
        return switch (grade) {
            case "A" -> 4.0;
            case "B" -> 3.0;
            case "C" -> 2.0;
            case "D" -> 1.0;
            default -> 0.0;
        };
    }
}
