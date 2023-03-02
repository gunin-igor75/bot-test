package ru.gil.bottest.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import ru.gil.bottest.entity.Report;

public interface ReportRepository extends JpaRepository<Report,Long> {
}