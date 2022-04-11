package com.example.activity1.web;

import com.example.activity1.entities.Student;
import com.example.activity1.services.StudentServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@AllArgsConstructor
public class StudentController {
    StudentServiceImpl studentService;

    @GetMapping("/home")
    public String home(Model model,
                       @RequestParam(name = "keyword", defaultValue = "") String keyword,
                       @RequestParam(name = "page", defaultValue = "0") int page,
                       @RequestParam(name = "size", defaultValue = "10") int size){
        Page<Student> studentsByPage = studentService.findStudentByName(keyword, page, size);
        model.addAttribute("keyword", keyword);
        model.addAttribute("students", studentsByPage.getContent());
        model.addAttribute("pages", new int[studentsByPage.getTotalPages()]);
        model.addAttribute("currentPage", page);

        return "home";
    }

    @GetMapping("/")
    public String home(){
        return "redirect:/home";
    }



}
