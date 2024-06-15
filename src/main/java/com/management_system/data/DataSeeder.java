package com.management_system.data;

import com.management_system.service.*;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class DataSeeder {

    private final AdminSeeder adminSeeder;
    private final MentorSeeder mentorSeeder;
    private final CourseSeeder courseSeeder;
    private final StudentSeeder studentSeeder;
    private final EnrollmentSeeder enrollmentSeeder;
    private final AdminService adminService;
    private final MentorService mentorService;
    private final CourseService courseService;
    private final StudentService studentService;
    private final EnrollmentService enrollmentService;

    @PostConstruct
    public void seedData() {
        if(isDatabaseEmpty()) {
            adminSeeder.setUp();
            mentorSeeder.setUp();
            courseSeeder.setUp();
            studentSeeder.setUp();
            enrollmentSeeder.setUp();
        }
    }

    public Boolean isDatabaseEmpty() {
        return adminService.isEmpty() && mentorService.isEmpty() && courseService.isEmpty() && studentService.isEmpty() && enrollmentService.isEmpty();
    }
}
