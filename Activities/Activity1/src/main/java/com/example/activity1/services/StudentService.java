package com.example.activity1.services;

import com.example.activity1.entities.Student;
import org.springframework.data.domain.Page;

import java.util.Optional;

public interface StudentService {
    Page<Student> findStudentByName(String name, int page, int size);
    void saveStudent(Student student);
    Student findById(Long id);
    void deleteStudent(Long id);
}
