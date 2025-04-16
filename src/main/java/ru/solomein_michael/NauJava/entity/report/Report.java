package ru.solomein_michael.NauJava.entity.report;

import jakarta.persistence.*;

@Entity
@Table(name = "reports")
public class Report {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private ReportStatus status;
    private String content;

    private Long firstPartCreatingTime;
    private Long secondPartCreatingTime;
    private Long totalCreatingTime;

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

    public Long getFirstPartCreatingTime(){
        return firstPartCreatingTime;
    }

    public Long getSecondPartCreatingTime(){
        return secondPartCreatingTime;
    }

    public Long getTotalCreatingTime(){
        return totalCreatingTime;
    }

    public void setFirstPartCreatingTime(Long firstPartCreatingTime) {
        this.firstPartCreatingTime = firstPartCreatingTime;
    }

    public void setSecondPartCreatingTime(Long secondPartCreatingTime) {
        this.secondPartCreatingTime = secondPartCreatingTime;
    }

    public void setTotalCreatingTime(Long totalCreatingTime) {
        this.totalCreatingTime = totalCreatingTime;
    }
}
