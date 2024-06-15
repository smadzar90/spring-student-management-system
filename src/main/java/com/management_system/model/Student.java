package com.management_system.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "students")
public class Student {

    public Student(String firstName, String lastName, String gender, String address, String state, String zipcode, String email,
                   String phoneNumber, LocalDate dateOfBirth, Double cumulativeGpa) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.address = address;
        this.state = state;
        this.zipcode = zipcode;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.dateOfBirth = dateOfBirth;
        this.cumulativeGpa = cumulativeGpa;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "First name is required")
    private String firstName;

    @NotBlank(message = "Last name is required")
    private String lastName;

    @Size(min=1, max=1, message = "Gender is required")
    private String gender;

    @NotBlank(message = "Address is required")
    private String address;

    @NotBlank(message = "State is required")
    private String state;

    @NotBlank(message = "Zipcode is required")
    @Pattern(regexp = "^.{0}$|^.{5}$", message = "Enter a valid zipcode")
    private String zipcode;

    @NotBlank(message = "Email is required")
    @Email(message = "Enter a valid email")
    private String email;

    @Pattern(regexp = "^\\(\\d{3}\\) \\d{3}-\\d{4}$|^$", message = "Phone number must be in the format (123) 123-1242")
    @NotBlank(message = "Phone number is required")
    private String phoneNumber;

    @NotNull(message = "Date of birth is required")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateOfBirth;

    private Double cumulativeGpa;

    @CreationTimestamp
    private LocalDateTime createdOn;

    @UpdateTimestamp
    private LocalDateTime updatedOn;

    @OneToMany(mappedBy = "student", cascade = CascadeType.REMOVE)
    private List<Enrollment> enrollments = new ArrayList<>();

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", first_name='" + firstName + '\'' +
                ", last_name='" + lastName + '\'' +
                ", gender='" + gender + '\'' +
                ", address= " + address  + '\'' +
                ", state= " + state + '\'' +
                ", zip_code= " + zipcode + '\'' +
                ", email= " + email + '\'' +
                ", phone_number= " + phoneNumber + '\'' +
                ", date_of_birth= " + dateOfBirth + '\'' +
                ", gpa= " + cumulativeGpa + '\'' +
                ", created_on= " + createdOn + '\'' +
                ", updated_on= " + updatedOn + '\'' +
                '}';
    }
}
