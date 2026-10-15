
package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.domain.Student;
import com.example.demo.repository.StudentRepository;

@Controller
public class RegisterController {

    private final StudentRepository repo;

    // Inject the repository through the constructor.
    public RegisterController(StudentRepository repo) {
        this.repo = repo;
    }

    // Display the registration page.
    @GetMapping("/signup")
    public String displaySignup() {
        return "register.html";
    }

    // Save the submitted student details.
    @PostMapping("/signup")
    public String signup(@ModelAttribute Student student) {
        repo.save(student);
        return "redirect:/index.html";
    }
}
