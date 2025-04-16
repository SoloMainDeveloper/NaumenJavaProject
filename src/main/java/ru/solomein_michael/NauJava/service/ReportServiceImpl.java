package ru.solomein_michael.NauJava.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.solomein_michael.NauJava.entity.Game;
import ru.solomein_michael.NauJava.entity.report.Report;
import ru.solomein_michael.NauJava.entity.report.ReportStatus;
import ru.solomein_michael.NauJava.repository.ReportRepository;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class ReportServiceImpl implements ReportService {
    @Autowired
    ReportRepository reportRepository;

    @Autowired
    UserService userService;

    @Autowired
    GameService gameService;

    @Override
    public String getReportContentById(Long id) {
        var report = reportRepository.findOneById(id);
        return report != null ? report.getContent() : null;
    }

    @Override
    public Long createReport(){
        var report = new Report();
        try{
            reportRepository.save(report);
            return report.getId();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void generateReport(Long id){
        CompletableFuture<Report> reportFuture = getReportAsync(id);

        try {
            Report report = reportFuture.join();
            reportRepository.save(report);
            System.out.println("Статус отчета: " + report.getStatus());
        } catch (Exception e) {
            System.err.println("Ошибка при генерации отчета: " + e.getMessage());
        }
    }

    public CompletableFuture<Report> getReportAsync(Long reportId) {
        var report = reportRepository.findOneById(reportId);
        long startTime = System.currentTimeMillis();

        CompletableFuture<Integer> userCountFuture = CompletableFuture.supplyAsync(() -> {
            try {
                return userService.getCount();
            } catch (RuntimeException e) {
                throw new RuntimeException(e);
            } finally {
                report.setFirstPartCreatingTime(System.currentTimeMillis() - startTime);
            }
        });

        CompletableFuture<List<Game>> objectsFuture = CompletableFuture.supplyAsync(() -> {
            try {
                return gameService.findAll();
            } catch (RuntimeException e) {
                throw new RuntimeException(e);
            } finally {
                report.setSecondPartCreatingTime(System.currentTimeMillis() - startTime);
            }
        });

        return userCountFuture.thenCombine(objectsFuture, (userCount, objects) -> {
            report.setStatus(ReportStatus.DONE);
            System.out.println("Количество пользователей: " + userCount);
            System.out.println("Список объектов: " + objects);
            return report;
        }).exceptionally(ex -> {
            report.setStatus(ReportStatus.ERROR);
            System.err.println("Произошла ошибка: " + ex.getMessage());
            return report;
        });
    }
}
