package com.example.activity1.services;

import com.example.activity1.entities.Student;
import org.springframework.data.domain.Page;

public interface StudentService {
    Page<Student> findStudentByName(String name, int page, int size);
    void addStudent(Student student);
}
