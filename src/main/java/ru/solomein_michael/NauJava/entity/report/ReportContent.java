package ru.solomein_michael.NauJava.entity.report;

import ru.solomein_michael.NauJava.dto.GameDto;

import java.util.List;

public class ReportContent {
    public ReportContent(){
    }

    public Long firstPartCreatingTime;
    public Long secondPartCreatingTime;
    public Long totalCreatingTime;
    public Integer userCount;
    public List<GameDto> games;
}
