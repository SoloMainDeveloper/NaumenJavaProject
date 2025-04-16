package ru.solomein_michael.NauJava.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import ru.solomein_michael.NauJava.entity.report.Report;

@RepositoryRestResource(path = "reports")
public interface ReportRepository extends JpaRepository<Report, Long> {
    Report findOneById(Long id);
}
