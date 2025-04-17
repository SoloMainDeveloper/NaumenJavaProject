package ru.solomein_michael.NauJava.service;

import ru.solomein_michael.NauJava.entity.report.Report;

public interface ReportService {
    String getReportContentById(Long id);
    Long createReport();
    void generateReport(Long id);
    Report getReportById(Long id);
}
