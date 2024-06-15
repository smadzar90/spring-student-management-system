package com.management_system.controller;

import com.management_system.model.Course;
import com.management_system.model.Student;
import com.management_system.service.CourseService;
import com.management_system.service.StudentService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RequestMapping("/studentsystem")
@Controller
@RequiredArgsConstructor
public class CourseController {

    private final CourseService courseService;
    private final StudentService studentService;

    @GetMapping("/courses")
    public String displayCoursesView(Model model, HttpSession session) {
        if (adminNotLoggedIn(session)) {
            return "redirect:/login";
        }

        List<Course> courses = courseService.getAllCoursesByIdAsc();
        setModelAttributes(model, courses);

        return "/course/courses";
    }

    @GetMapping("/course/details/{id}")
    public String displayCourseDetailsView(@PathVariable("id") String id, Model model, HttpSession session) {
        if (adminNotLoggedIn(session)) {
            return "redirect:/login";
        }

        Course course = courseService.getCourseByStringId(id);
        if (course == null) {
            return "redirect:/studentsystem/courses";
        }

        setModelAttributes(model, course);
        return "course/details";
    }

    @GetMapping("/courses/registration/{id}")
    public String displayStudentCoursesRegistrationView(@PathVariable("id") String id, Model model, HttpSession session) {
        if (adminNotLoggedIn(session)) {
            return "redirect:/login";
        }

        Student student = studentService.getStudentByStringId(id);
        if (student == null) {
            return "redirect:/studentsystem/students/search/registration";
        }

        List<Course> courses = studentService.getUnregisteredCoursesByStudent(student);
        setModelAttributes(model, student, courses, "Course Registration", "dashboardActive");

        return "course/course_registration";
    }

    @PutMapping("/courses/register/{studentId}/{courseId}")
    public String registerCourse(@PathVariable("studentId") Long studentId, @PathVariable("courseId") Long courseId, HttpSession session) {
        if (session.getAttribute("username") == null) {
            return "redirect:/login";
        }

        studentService.addEnrollment(studentId, courseId);
        return "redirect:/studentsystem/courses/registration/" + studentId;
    }

    @GetMapping("/courses/deletion/{id}")
    public String displayStudentCoursesDeletionView(@PathVariable("id") String id, Model model, HttpSession session) {
        if (adminNotLoggedIn(session)) {
            return "redirect:/login";
        }

        Student student = studentService.getStudentByStringId(id);

        if (student == null) {
            return "redirect:/studentsystem/students/search/deletion";
        }

        List<Course> registeredCourses = studentService.getRegisteredCoursesByStudent(student);
        setModelAttributes(model, student, registeredCourses, "Course Deletion", "dashboardActive");

        return "course/course_deletion";
    }

    @DeleteMapping("/courses/drop/{studentId}/{courseId}")
    public String dropCourse(@PathVariable("studentId") Long studentId, @PathVariable("courseId") Long courseId, HttpSession session) {
        if (adminNotLoggedIn(session)) {
            return "redirect:/login";
        }

        studentService.deleteEnrollment(studentId, courseId);
        return "redirect:/studentsystem/courses/deletion/" + studentId;
    }

    @GetMapping("/courses/completion/{id}")
    public String displayStudentCourseCompletionView(@PathVariable("id") String id, Model model, HttpSession session) {
        if (adminNotLoggedIn(session)) {
            return "redirect:/login";
        }

        Student student = studentService.getStudentByStringId(id);

        if (student == null) {
            return "redirect:/studentsystem/students/search/completion";
        }

        List<Course> registeredCourses = studentService.getRegisteredCoursesByStudent(student);
        setModelAttributes(model, student, registeredCourses, "Course Completion", "dashboardActive");

        return "course/course_completion";
    }

    @GetMapping("courses/complete/{studentId}/{courseId}")
    public String completeCourseProperties(@PathVariable("studentId") String studentId, @PathVariable("courseId") String courseId, HttpSession session, Model model) {
        if (adminNotLoggedIn(session)) {
            return "redirect:/login";
        }

        Student student = studentService.getStudentByStringId(studentId);
        Course course = courseService.getCourseByStringId(courseId);

        if (student == null || course == null) {
            return "redirect:/studentsystem/students/search/completion";
        }

        List<Course> courses = Collections.singletonList(course);
        setModelAttributes(model, student, courses, "Course Completion", "dashboardActive");

        return "course/course_completion_properties";

    }

    @PutMapping("courses/complete/{studentId}/{courseId}")
    public String completeCourse(@RequestParam String grade, @PathVariable("studentId") Long studentId, @PathVariable("courseId") Long courseId, HttpSession session) {
        if (adminNotLoggedIn(session)) {
            return "redirect:/login";
        }

        studentService.completeEnrollment(studentId, courseId, grade);
        return "redirect:/studentsystem/courses/completion/" + studentId;
    }

    private Boolean adminNotLoggedIn(HttpSession session) {
        return session.getAttribute("username") == null;
    }

    private void setModelAttributes(Model model, Student student, List<Course> courses, String title, String activated) {
        model.addAttribute("student", student);
        model.addAttribute("courses", courses);
        model.addAttribute("title", title);
        model.addAttribute(activated, true);
    }

    private void setModelAttributes(Model model, List<Course> courses) {
        model.addAttribute("courses", courses);
        model.addAttribute("title", "Courses");
        model.addAttribute("coursesActive", true);
    }

    private void setModelAttributes(Model model, Course course) {
        model.addAttribute("course", course);
        model.addAttribute("title", "Course Details");
        model.addAttribute("coursesActive", true);
    }
}
