package com.example.activity1.services;

import com.example.activity1.entities.Student;
import com.example.activity1.repositories.StudentRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

@AllArgsConstructor
public class StudentServiceImpl implements StudentService {
    StudentRepository studentRepository;

    @Override
    public Page<Student> findStudentByName(String name, int page, int size) {
        return studentRepository.findByName(name, PageRequest.of(page, size));
    }
}
