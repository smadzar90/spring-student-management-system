package com.management_system.repository;

import com.management_system.model.Enrollment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {
    List<Enrollment> findAllByCompletedAndStudentId(Boolean completed, long studentId);

    List<Enrollment> findAllByStudentId(Long studentId);

    @Query(value = "SELECT course_id FROM Enrollments WHERE student_id = :studentId", nativeQuery = true)
    List<Long> findAllCourseIdsByStudentId(@Param("studentId") Long studentId);

    Optional<Enrollment> findByStudentIdAndCourseId(Long studentId, Long courseId);
}
