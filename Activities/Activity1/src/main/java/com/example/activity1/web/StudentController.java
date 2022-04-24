package com.example.activity1.web;

import com.example.activity1.entities.Student;
import com.example.activity1.services.StudentServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;

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

    //delete
    @DeleteMapping("/admin/delete")
    public String delete(@RequestParam(name = "id") long id) {
        Student student = studentService.findById(id);
        if(student!=null)
            studentService.deleteStudent(id);
        else
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Student deosn't exist");
        return "redirect:/home";
    }

    //add
    @GetMapping("/admin/new")
    public String addForm(Model model, int page, String keyword){
        model.addAttribute("page", page);
        model.addAttribute("keyword", keyword);
        model.addAttribute("student",new Student());
        return "addStudent";
    }

    //edit
    @GetMapping("/admin/edit")
    public String editForm(Model model, long id, String keyword, int page){
        Student student = studentService.findById(id);
        if(student==null) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Student deosn't exist");
        System.out.println(student.getLastname());
        model.addAttribute("page", page);
        model.addAttribute("keyword", keyword);
        model.addAttribute("student",student);
        return "editStudent";
    }

    //save
    @PostMapping(path="/admin/save")
    public String save(Model model, @Valid Student student, BindingResult bindingResult, @RequestParam(name = "keyword",defaultValue = "")String keyword,
                       @RequestParam(name = "page",defaultValue = "0") int page){
        if(bindingResult.hasErrors()) return "addStudent";
        studentService.saveStudent(student);
        return "redirect:/home?page="+page+"&keyword="+keyword;
    }

}
