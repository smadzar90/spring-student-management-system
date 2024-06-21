package com.management_system.data;

import com.management_system.model.Student;
import com.management_system.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class StudentSeeder {
    private final StudentService studentService;

    /**
     * Seed the data to student table only if StudentSystemDB is empty
     * Default data will be seeded
     */
    public void setUp() {
        List<Student> students = new ArrayList<>();
        students.add(new Student("Mark", "Hughes", "M", "92 Freshour Circle", "Pennsylvania", "16634", "markhughes@gmail.com", "(942) 124-6562",
                LocalDate.of(2000, 1, 8), null));
        students.add(new Student("Jane", "Doe", "F", "123 Main St", "New York", "10001", "jane.doe@gmail.com", "(555) 123-4501",
                LocalDate.of(1998, 2, 14), null));
        students.add(new Student("John", "Smith", "M", "456 Oak Dr", "California", "90210", "john.smith@gmail.com", "(555) 123-4502",
                LocalDate.of(1997, 3, 21), null));
        students.add(new Student("Emily", "Johnson", "F", "789 Pine Ln", "Texas", "73301", "emily.johnson@gmail.com", "(555) 123-4503",
                LocalDate.of(2000, 4, 30), null));
        students.add(new Student("Michael", "Brown", "M", "101 Maple Ave", "Florida", "33101", "michael.brown@gmail.com", "(555) 123-4504",
                LocalDate.of(1999, 5, 15), null));
        students.add(new Student("Sarah", "Davis", "F", "202 Birch St", "Illinois", "60007", "sarah.davis@gmail.com", "(555) 123-4505",
                LocalDate.of(1996, 6, 10), null));
        students.add(new Student("David", "Wilson", "M", "303 Cedar Blvd", "Ohio", "44101", "david.wilson@gmail.com", "(555) 123-4506",
                LocalDate.of(1998, 7, 25), null));
        students.add(new Student("Laura", "Martinez", "F", "404 Elm Cir", "Arizona", "85001", "laura.martinez@gmail.com", "(555) 123-4507",
                LocalDate.of(1997, 8, 19), null));
        students.add(new Student("James", "Garcia", "M", "505 Fir Pl", "Nevada", "89501", "james.garcia@gmail.com", "(555) 123-4508",
                LocalDate.of(1995, 9, 23), null));
        students.add(new Student("Anna", "Anderson", "F", "606 Spruce Way", "Georgia", "30301", "anna.anderson@gmail.com", "(555) 123-4509",
                LocalDate.of(1994, 10, 12), null));
        students.add(new Student("Christopher", "Thomas", "M", "707 Ash Ct", "Michigan", "48201", "christopher.thomas@gmail.com", "(555) 123-4510",
                LocalDate.of(1996, 11, 5), null));
        students.add(new Student("Jessica", "Moore", "F", "808 Redwood Dr", "Washington", "98001", "jessica.moore@gmail.com", "(555) 123-4511",
                LocalDate.of(1997, 12, 1), null));
        students.add(new Student("Daniel", "Jackson", "M", "909 Cypress St", "Oregon", "97001", "daniel.jackson@gmail.com", "(555) 123-4512",
                LocalDate.of(1998, 1, 30), null));
        students.add(new Student("Karen", "White", "F", "1010 Palm Ave", "Colorado", "80201", "karen.white@gmail.com", "(555) 123-4513",
                LocalDate.of(1999, 2, 15), null));
        students.add(new Student("Paul", "Harris", "M", "1111 Willow Rd", "Massachusetts", "02101", "paul.harris@gmail.com", "(555) 123-4514",
                LocalDate.of(2000, 3, 20), null));
        students.add(new Student("Nancy", "Martin", "F", "1212 Poplar St", "Minnesota", "55101", "nancy.martin@gmail.com", "(555) 123-4515",
                LocalDate.of(1997, 4, 25), null));
        students.add(new Student("Steven", "Clark", "M", "1313 Magnolia Blvd", "Tennessee", "37201", "steven.clark@gmail.com", "(555) 123-4516",
                LocalDate.of(1998, 5, 30), null));
        students.add(new Student("Barbara", "Lewis", "F", "1414 Pineapple Ln", "Kentucky", "40201", "barbara.lewis@gmail.com", "(555) 123-4517",
                LocalDate.of(1996, 6, 15), null));
        students.add(new Student("Kevin", "Robinson", "M", "1515 Mango Dr", "Indiana", "46201", "kevin.robinson@gmail.com", "(555) 123-4518",
                LocalDate.of(1995, 7, 10), null));
        students.add(new Student("Susan", "Walker", "F", "1616 Peach Pl", "Missouri", "63101", "susan.walker@gmail.com", "(555) 123-4519",
                LocalDate.of(1994, 8, 5), null));
        students.add(new Student("Brian", "Young", "M", "1717 Cherry Cir", "Wisconsin", "53201", "brian.young@gmail.com", "(555) 123-4520",
                LocalDate.of(1993, 9, 25), null));
        students.add(new Student("Patricia", "King", "F", "1818 Strawberry Way", "Maryland", "21201", "patricia.king@gmail.com", "(555) 123-9521",
                LocalDate.of(1992, 10, 20), null));
        students.add(new Student("Charles", "Wright", "M", "1919 Blueberry Ct", "Virginia", "23218", "charles.wright@gmail.com", "(555) 123-4522",
                LocalDate.of(1991, 11, 15), null));
        students.add(new Student("Linda", "Lopez", "F", "2020 Blackberry Ln", "North Carolina", "27601", "linda.lopez@gmail.com", "(555) 123-4523",
                LocalDate.of(1990, 12, 10), null));
        students.add(new Student("George", "Hill", "M", "2121 Raspberry Rd", "South Carolina", "29201", "george.hill@gmail.com", "(555) 123-1524",
                LocalDate.of(1989, 1, 5), null));
        students.add(new Student("Carol", "Scott", "F", "2222 Cranberry St", "Alabama", "35201", "carol.scott@gmail.com", "(555) 123-4525",
                LocalDate.of(1988, 2, 29), null));
        students.add(new Student("Edward", "Green", "M", "2323 Grape Ave", "Louisiana", "70112", "edward.green@gmail.com", "(555) 123-4526",
                LocalDate.of(1987, 3, 25), null));
        students.add(new Student("Margaret", "Adams", "F", "2424 Apple Blvd", "Mississippi", "39201", "margaret.adams@gmail.com", "(555) 123-4527",
                LocalDate.of(1986, 4, 20), null));
        students.add(new Student("Thomas", "Baker", "M", "2525 Banana Dr", "Arkansas", "72201", "thomas.baker@gmail.com", "(555) 123-4528",
                LocalDate.of(1985, 5, 15), null));
        students.add(new Student("Elizabeth", "Gonzalez", "F", "2626 Melon Pl", "Oklahoma", "73101", "elizabeth.gonzalez@gmail.com", "(555) 124-5529",
                LocalDate.of(1984, 6, 10), null));

        studentService.saveAllStudents(students);
        System.out.println("Student records seeded to database!");
    }
}

