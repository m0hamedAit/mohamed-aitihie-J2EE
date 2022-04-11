package com.example.activity1.repositories;

import com.example.activity1.entities.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface StudentRepository extends JpaRepository<Student, String> {
    @Query("select s from Student s where s.firstname like %:key% or s.lastname like %:key%")
    Page<Student> findByName(@Param("key")String keyword, Pageable pageable);

    //Page<Student> findStudentsByFirstnameContainsOrLastnameContains(String keyword);
}
