package com.management_system.repository;

import com.management_system.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    List<Student> findAllByOrderByUpdatedOnDesc();

    List<Student> findAllByOrderByCumulativeGpaAsc();

    List<Student> findAllByOrderByCumulativeGpaDesc();

    List<Student> findAllByOrderByIdAsc();

    List<Student> findAllByOrderByIdDesc();

    List<Student> findAllByOrderByDateOfBirthAsc();

    List<Student> findAllByOrderByDateOfBirthDesc();
}
