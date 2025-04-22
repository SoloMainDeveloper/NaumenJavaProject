package ru.solomein_michael.NauJava.controller;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.solomein_michael.NauJava.entity.report.ReportContent;
import ru.solomein_michael.NauJava.entity.report.ReportStatus;
import ru.solomein_michael.NauJava.restController.CreateReportRestController;
import ru.solomein_michael.NauJava.service.ReportService;

@Controller
@RequestMapping("/create-report")
public class CreateReportController {
    @Autowired
    CreateReportRestController restController;

    @Autowired
    ReportService reportService;

    @GetMapping("/view")
    public String getGames(Model model) {
        return "createReport";
    }

    @GetMapping("/get-report")
    public String getReportById(Model model, Long id){
        var report = reportService.getReportById(id);
        switch (report.getStatus()){
            case ReportStatus.DONE -> {
                var reportContent = new Gson().fromJson(report.getContent(), ReportContent.class);
                model.addAttribute("reportContent", reportContent);
                return "reportSuccessful";
            }
            case ReportStatus.ERROR -> {
                return "reportError";
            }
            case ReportStatus.CREATED -> {
                return "reportIsNotReady";
            }
            default -> throw new RuntimeException("Report Status Error");
        }
    }
}
