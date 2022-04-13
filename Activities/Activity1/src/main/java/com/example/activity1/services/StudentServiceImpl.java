package com.example.activity1.services;

import com.example.activity1.entities.Student;
import com.example.activity1.repositories.StudentRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class StudentServiceImpl implements StudentService {
    StudentRepository studentRepository;

    @Override
    public Page<Student> findStudentByName(String name, int page, int size) {
        return studentRepository.findByName(name, PageRequest.of(page, size));
    }

    @Override
    public void saveStudent(Student student) {
        studentRepository.save(student);
    }

    @Override
    public Student findById(Long id) {
        return studentRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteStudent(Long id) {
        studentRepository.findById(id).ifPresent(student -> studentRepository.delete(student));
    }
}
