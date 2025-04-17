package ru.solomein_michael.NauJava.entity.report;

import jakarta.persistence.*;

@Entity
@Table(name = "reports")
public class Report {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private ReportStatus status;

    @Column(length = 2048)
    private String content;

    public Report(){
        this.status = ReportStatus.CREATED;
    }

    public ReportStatus getStatus(){
        return status;
    }

    public void setStatus(ReportStatus status){
        this.status = status;
    }

    public String getContent(){
        return content;
    }

    public Long getId(){
        return id;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
