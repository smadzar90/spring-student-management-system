package com.management_system.controller;

import com.management_system.model.Enrollment;
import com.management_system.model.Student;
import com.management_system.service.CourseService;
import com.management_system.service.EnrollmentService;
import com.management_system.service.MentorService;
import com.management_system.service.StudentService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/studentsystem")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;
    private final CourseService courseService;
    private final MentorService mentorService;
    private final EnrollmentService enrollmentService;

    @GetMapping("")
    public String displayDashboardView(HttpSession session, Model model) {
        if (adminNotLoggedIn(session)) {
            return "redirect:/login";
        }

        model.addAttribute("courses_count", courseService.getAllCourses().size());
        model.addAttribute("students_count", studentService.getAllStudents().size());
        model.addAttribute("mentors_count", mentorService.getAllMentors().size());
        model.addAttribute("title", "Dashboard");
        model.addAttribute("dashboardActive", true);
        return "student/dashboard";
    }

    @GetMapping("/students")
    public String displayStudentsView(@RequestParam(value = "filter", required = false) String filter, HttpSession session, Model model) {
        if (adminNotLoggedIn(session)) {
            return "redirect:/login";
        }

        List<Student> students = filterStudents(filter);
        setModelAttributes(model, students, "Students");
        model.addAttribute("studentsActive", true);
        return "student/students";
    }

    @GetMapping("/students/add")
    public String displayAddStudentView(HttpSession session, Model model) {
        if (adminNotLoggedIn(session)) {
            return "redirect:/login";
        }

        setModelAttributes(model, new Student(), "Add Student");
        return "student/add_student";
    }

    @PostMapping("/students/add")
    public String addStudent(@Valid @ModelAttribute("student") Student student, BindingResult bindingResult, Model model, HttpSession session) {
        if (adminNotLoggedIn(session)) {
            return "redirect:/login";
        }

        if (bindingResult.hasErrors()) {
            setModelAttributes(model, student, "Add Student");
            return "student/add_student";
        }

        studentService.saveStudent(student);
        return "redirect:/studentsystem/students";
    }

    @GetMapping("/students/update")
    public String displayUpdateStudentListView(HttpSession session, Model model) {
        if (adminNotLoggedIn(session)) {
            return "redirect:/login";
        }
        List<Student> students = studentService.getAllStudentsByUpdatedOnDesc();
        setModelAttributes(model, students, "Update Student");

        return "student/update_student_list";
    }

    @GetMapping("/students/update/{id}")
    public String displayUpdateStudentFormView(@PathVariable String id, HttpSession session, Model model) {
        if (adminNotLoggedIn(session)) {
            return "redirect:/login";
        }

        Student student = studentService.getStudentByStringId(id);
        if (student == null) {
            return "redirect:/studentsystem/students/update";
        }
        setModelAttributes(model, student, "Update Student");

        return "/student/update_student";
    }

    @PutMapping("/students/update/{id}")
    public String updateStudent(@PathVariable Long id, @Valid @ModelAttribute Student student, BindingResult bindingResult, Model model, HttpSession session) {
        if (adminNotLoggedIn(session)) {
            return "redirect:/login";
        }

        Student existingStudent = studentService.getStudentById(id);

        if (existingStudent == null) {
            return "redirect:/studentsystem/students/update";
        }

        if (bindingResult.hasErrors()) {
            setModelAttributes(model, student, "Update Student");
            return "student/update_student";
        }

        studentService.updateStudent(student);
        return "redirect:/studentsystem/students/update";
    }

    @GetMapping("/students/delete")
    public String displayDeleteStudentView(HttpSession session, Model model) {
        if (adminNotLoggedIn(session)) {
            return "redirect:/login";
        }

        List<Student> students = studentService.getAllStudentsByUpdatedOnDesc();
        setModelAttributes(model, students, "Delete Student");
        return "student/delete_student";
    }

    @DeleteMapping("/students/delete/{id}")
    public String deleteStudent(@PathVariable Long id, HttpSession session) {
        if (adminNotLoggedIn(session)) {
            return "redirect:/login";
        }

        studentService.deleteStudentById(id);
        return "redirect:/studentsystem/students/delete";
    }

    @GetMapping("/students/search/registration")
    public String displayStudentSearchForRegistrationView(@RequestParam(value = "id", required = false) String id, Model model, HttpSession session) {
        if (adminNotLoggedIn(session)) {
            return "redirect:/login";
        }

        setModelAttributes(model, "registration");
        return getString(id, model);
    }

    @GetMapping("/students/search/deletion")
    public String displayStudentSearchForDeletion(@RequestParam(value = "id", required = false) String id, Model model, HttpSession session) {
        if (adminNotLoggedIn(session)) {
            return "redirect:/login";
        }

        setModelAttributes(model, "deletion");
        return getString(id, model);
    }

    @GetMapping("/students/search/completion")
    public String displayStudentSearchForCompletion(@RequestParam(value = "id", required = false) String id, Model model, HttpSession session) {
        if (adminNotLoggedIn(session)) {
            return "redirect:/login";
        }

        setModelAttributes(model, "completion");
        return getString(id, model);
    }

    @GetMapping("/students/search/activity")
    public String displayStudentSearchForActivity(@RequestParam(value = "id", required = false) String id, Model model, HttpSession session) {
        if (adminNotLoggedIn(session)) {
            return "redirect:/login";
        }

        setModelAttributes(model, "activity");
        return getString(id, model);
    }

    @GetMapping("/student/activity/{id}")
    public String displayStudentActivity(@PathVariable String id, Model model, HttpSession session) {
        if (adminNotLoggedIn(session)) {
            return "redirect:/login";
        }

        Student student = studentService.getStudentByStringId(id);
        if (student == null) {
            return "redirect:/studentsystem/students/search/activity";
        }

        List<Enrollment> completedEnrollments = enrollmentService.getCompletedEnrollmentsByStudent(Long.parseLong(id));
        List<Enrollment> incompleteEnrollments = enrollmentService.getNotCompletedEnrollmentsByStudent(Long.parseLong(id));
        setModelAttributes(model, student, "Student Activity");
        model.addAttribute("completedEnrollments", completedEnrollments);
        model.addAttribute("notCompletedEnrollments", incompleteEnrollments);
        return "student/activity";
    }

    private String getString(@RequestParam(value = "id", required = false) String id, Model model) {
        if (id == null) {
            return "search";
        }

        Student student = studentService.getStudentByStringId(id);

        if (student == null) {
            model.addAttribute("notFound", true);
            return "search";
        }

        Boolean deletion = (Boolean) model.getAttribute("deletion");
        Boolean completion = (Boolean) model.getAttribute("completion");
        Boolean activity = (Boolean) model.getAttribute("activity");

        if (Boolean.TRUE.equals(deletion)) {
            return "redirect:/studentsystem/courses/deletion/" + Long.parseLong(id);
        } else if (Boolean.TRUE.equals(completion)) {
            return "redirect:/studentsystem/courses/completion/" + Long.parseLong(id);
        } else if (Boolean.TRUE.equals(activity)) {
            return "redirect:/studentsystem/student/activity/" + Long.parseLong(id);
        }

        return "redirect:/studentsystem/courses/registration/" + Long.parseLong(id);
    }

    private Boolean adminNotLoggedIn(HttpSession session) {
        return session.getAttribute("username") == null;
    }

    private void setModelAttributes(Model model, Student student, String title) {
        model.addAttribute("title", title);
        model.addAttribute("student", student);
    }

    private void setModelAttributes(Model model, List<Student> students, String title) {
        model.addAttribute("title", title);
        model.addAttribute("students", students);
    }

    private void setModelAttributes(Model model, String action) {
        model.addAttribute("title", "Search Student");
        model.addAttribute(action, true);
    }

    private List<Student> filterStudents(String filter) {
        if (filter == null) {
            return studentService.getAllStudentsByUpdatedOnDesc();
        }

        return switch (filter) {
            case "id" -> studentService.getAllStudentsByID();
            case "id_desc" -> studentService.getAllStudentsByIDDesc();
            case "gpa" -> studentService.getAllStudentsByGPA();
            case "gpa_desc" -> studentService.getAllStudentsByGPADesc();
            case "dob" -> studentService.getAllStudentsByDOB();
            case "dob_desc" -> studentService.getAllStudentsByDOBDesc();
            default -> studentService.getAllStudentsByUpdatedOnDesc();
        };
    }
}
