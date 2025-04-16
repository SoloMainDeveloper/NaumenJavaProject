package ru.solomein_michael.NauJava.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/create-report")
public class CreateReportController {
    @GetMapping("/view")
    public String getGames(Model model) {
        return "createReport";
    }
}
