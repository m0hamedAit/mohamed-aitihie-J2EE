package com.example.activity1;

import com.example.activity1.entities.Sexe;
import com.example.activity1.entities.Student;
import com.example.activity1.security.service.SecurityService;
import com.example.activity1.services.StudentServiceImpl;
import com.github.javafaker.Faker;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.ViewResolver;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

@SpringBootApplication
public class Activity1Application {
    public static void main(String[] args) {
        SpringApplication.run(Activity1Application.class, args);
    }

    //@Bean
    CommandLineRunner addStudents(StudentServiceImpl studentService) {
        Faker faker = new Faker();
        return args -> {
            for (int i = 0; i < 50; i++) {
                studentService.saveStudent(
                        new Student(null, faker.name().firstName(), faker.name().lastName(), faker.internet().emailAddress(), faker.date().birthday(19, 21), faker.bool().bool(), faker.options().option(Sexe.class))
                );
            }
        };
    }

    //@Bean
    CommandLineRunner saveUsers(SecurityService securityService) {
        return args -> {
            securityService.saveUser("mohamed", "1234", "1234");
            securityService.saveUser("Anas", "12345", "12345");

            securityService.saveRole("USER", "");
            securityService.saveRole("ADMIN", "");

            securityService.addRoleToUser("mohamed", "USER");
            securityService.addRoleToUser("mohamed", "ADMIN");
            securityService.addRoleToUser("Anas", "USER");
        };
    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public ViewResolver viewResolver() {
        ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
        templateResolver.setTemplateMode("XHTML");
        templateResolver.setPrefix("views/");
        templateResolver.setSuffix(".html");

        SpringTemplateEngine engine = new SpringTemplateEngine();
        engine.setTemplateResolver(templateResolver);

        ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
        viewResolver.setTemplateEngine(engine);
        return viewResolver;
    }
}
