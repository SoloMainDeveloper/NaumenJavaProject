package ru.solomein_michael.NauJava.service;

public interface ReportService {
    String getReportContentById(Long id);
    Long createReport();
    void generateReport(Long id);
}
