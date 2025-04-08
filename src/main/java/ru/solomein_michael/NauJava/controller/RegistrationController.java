package ru.solomein_michael.NauJava.controller;

import org.springframework.stereotype.Controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ru.solomein_michael.NauJava.entity.CustomUserDetails;
import ru.solomein_michael.NauJava.service.UserService;

@Controller
public class RegistrationController {
    @Autowired
    private UserService userService;

    @GetMapping("/registration")
    public String registration()
    {
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(CustomUserDetails user, Model model)
    {
        try
        {
            userService.addUser(user);
            return "redirect:/login";
        }
        catch (Exception ex)
        {
            model.addAttribute("message", "User exists");
            return "registration";
        }
    }

}
