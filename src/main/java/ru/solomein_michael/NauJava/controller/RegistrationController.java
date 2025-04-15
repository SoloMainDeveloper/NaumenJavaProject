package ru.solomein_michael.NauJava.controller;

import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;
import ru.solomein_michael.NauJava.entity.CustomUserDetails;

@Controller
public class RegistrationController {
    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/registration")
    public String registration()
    {
        return "registration";
    }

    @PostMapping("/registration")
    public String registerUser(@ModelAttribute("user") CustomUserDetails user, Model model) {
        var response = restTemplate.postForEntity("http://localhost:8080/api/register", user, CustomUserDetails.class);
        if (response.getStatusCode().is2xxSuccessful()) {
            model.addAttribute("message", "User registered successfully!");
            return "registrationSuccess";
        } else {
            model.addAttribute("error", "Registration failed: " + response.getBody());
            return "registration";
        }
    }
}
