package ru.solomein_michael.NauJava.restController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.solomein_michael.NauJava.entity.CustomUserDetails;
import ru.solomein_michael.NauJava.entity.report.Report;
import ru.solomein_michael.NauJava.service.ReportService;

import java.util.Collections;

@RestController
@RequestMapping("/api")
public class CreateReportRestController {
    @Autowired
    ReportService reportService;

    @PostMapping("/create-report")
    public ResponseEntity<?> addUser() {
        try {
            var id = reportService.createReport();
            reportService.generateReport(id);
            return ResponseEntity.status(HttpStatus.CREATED).body(Collections.singletonMap("id", id));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Collections.singletonMap("message", "An error occurred"));
        }
    }

    @GetMapping("/get-report")
    public String getReportById(Long id){
        return reportService.getReportContentById(id);
    }
}
