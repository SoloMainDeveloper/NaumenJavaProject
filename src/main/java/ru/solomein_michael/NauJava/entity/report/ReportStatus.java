package ru.solomein_michael.NauJava.entity.report;

public enum ReportStatus {
    CREATED("CREATED"),
    DONE("DONE"),
    ERROR("ERROR");

    private String status;

    ReportStatus(String status) {
        this.status = status;
    }
}
