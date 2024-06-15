package com.management_system.data;

import com.management_system.model.Course;
import com.management_system.service.CourseService;
import com.management_system.service.MentorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class CourseSeeder {

    private final CourseService courseService;
    private final MentorService mentorService;

    /**
     * Seed the data to course table only if table is empty
     * Course table should never be empty after the seed
     */
    public void setUp() {
        List<Course> courses = new ArrayList<>();
        courses.add(new Course("Introduction to Computer Science", "This course provides an overview of computer science, including programming, algorithms, data structures, and the basics of software development.", "CS1010", 0, 0, mentorService.getMentorById(1)));
        courses.add(new Course("Data Structures", "This course covers the fundamental data structures in computer science, such as arrays, linked lists, stacks, queues, trees, and graphs.", "CS1020", 0, 0, mentorService.getMentorById(2)));
        courses.add(new Course("Algorithms", "This course introduces the design and analysis of algorithms, focusing on sorting, searching, and optimization techniques.", "CS1030", 0, 0, mentorService.getMentorById(3)));
        courses.add(new Course("Computer Architecture", "This course explores the structure and organization of computer systems, including processors, memory, and input/output devices.", "CS1040", 0, 0, mentorService.getMentorById(4)));
        courses.add(new Course("Operating Systems", "This course covers the principles of operating systems, including process management, memory management, file systems, and security.", "CS1050", 0, 0, mentorService.getMentorById(5)));
        courses.add(new Course("Database Systems", "This course provides an introduction to database design, implementation, and management, focusing on relational databases and SQL.", "CS1060", 0, 0, mentorService.getMentorById(6)));
        courses.add(new Course("Software Engineering", "This course introduces the principles and practices of software engineering, including project management, software development methodologies, and testing.", "CS1070", 0, 0, mentorService.getMentorById(6)));
        courses.add(new Course("Web Development", "This course covers the basics of web development, including HTML, CSS, JavaScript, and web frameworks.", "CS1080", 0, 0, mentorService.getMentorById(5)));
        courses.add(new Course("Machine Learning", "This course provides an introduction to machine learning, including supervised and unsupervised learning, neural networks, and deep learning.", "CS1090", 0, 0, mentorService.getMentorById(4)));
        courses.add(new Course("Artificial Intelligence", "This course covers the fundamentals of artificial intelligence, including problem-solving, knowledge representation, and reasoning.", "CS1100", 0, 0, mentorService.getMentorById(3)));
        courses.add(new Course("Cybersecurity", "This course explores the principles of cybersecurity, including threats, vulnerabilities, cryptography, and network security.", "CS1110", 0, 0, mentorService.getMentorById(2)));
        courses.add(new Course("Human-Computer Interaction", "This course covers the principles and practices of designing user interfaces and user experiences for interactive systems.", "CS1120", 0, 0, mentorService.getMentorById(1)));
        courses.add(new Course("Mobile App Development", "This course provides an introduction to developing mobile applications, focusing on iOS and Android platforms.", "CS1130", 0, 0, mentorService.getMentorById(2)));
        courses.add(new Course("Cloud Computing", "This course explores cloud computing concepts and technologies, including virtualization, cloud storage, and cloud services.", "CS1140", 0, 0, mentorService.getMentorById(3)));

        courseService.saveAllCourses(courses);
        System.out.println("Course records seeded to database!");
    }
}
