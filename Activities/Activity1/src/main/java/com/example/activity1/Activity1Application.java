package com.example.activity1;

import com.example.activity1.entities.Sexe;
import com.example.activity1.entities.Student;
import com.example.activity1.services.StudentService;
import com.example.activity1.services.StudentServiceImpl;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;

@SpringBootApplication
public class Activity1Application {
    public static void main(String[] args) {
        SpringApplication.run(Activity1Application.class, args);
    }

    @Bean
    CommandLineRunner addUser(StudentServiceImpl studentService){
        return args -> {
          studentService.addStudent(new Student(null,"Mohamed", "Ait", "mohamed@gmail.com", new Date(), true, Sexe.MALE));
          studentService.addStudent(new Student(null,"Amine", "C", "amine@gmail.com", new Date(), false, Sexe.MALE));
          studentService.addStudent(new Student(null,"Asmaa", "G", "asmaa@gmail.com", new Date(), true, Sexe.FEMALE));
          studentService.addStudent(new Student(null,"Amina", "D", "amina@gmail.com", new Date(), true, Sexe.FEMALE));
        };
    }
}
