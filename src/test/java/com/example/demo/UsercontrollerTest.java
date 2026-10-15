
package com.example.demo;

import com.example.demo.controller.LoginController;
import com.example.demo.domain.Student;
import com.example.demo.repository.StudentRepository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(LoginController.class)
public class UsercontrollerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private StudentRepository repo;

    @Test
    public void testLoginWithCorrectCredentials() throws Exception {

        // Create a sample student with valid credentials.
        Student user = new Student();
        user.setUsername("testuser");
        user.setPassword("testpassword");

        // Mock the repository response for valid credentials.
        when(repo.findByUsernameAndPassword(
                "testuser", "testpassword")).thenReturn(user);

        // Submit the login form with valid credentials.
        mockMvc.perform(post("/signin")
                .param("username", "testuser")
                .param("password", "testpassword"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/dashboard.html"));
    }

    @Test
    public void testLoginWithIncorrectCredentials() throws Exception {

        // Return null when the credentials are invalid.
        when(repo.findByUsernameAndPassword(
                "wronguser", "wrongpassword")).thenReturn(null);

        // Submit the login form with invalid credentials.
        mockMvc.perform(post("/signin")
                .param("username", "wronguser")
                .param("password", "wrongpassword"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/signin"));
    }
}
