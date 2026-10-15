
package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.domain.Student;
import com.example.demo.repository.StudentRepository;

@Controller
public class LoginController {

    @Autowired
    private StudentRepository repo;

    @GetMapping("/signin")
    public String showLoginPage() {
        return "login.html";
    }

    @PostMapping("/signin")
    public String login(
            @RequestParam String username,
            @RequestParam String password) {

        Student entity =
                repo.findByUsernameAndPassword(username, password);

        if (entity == null) {
            return "redirect:/signin";
        }

        return "redirect:/dashboard.html";
    }

    @GetMapping("/logout")
    public String logout() {
        return "redirect:/index.html";
    }
}
