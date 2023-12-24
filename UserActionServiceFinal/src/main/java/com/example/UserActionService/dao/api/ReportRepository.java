package com.example.UserActionService.dao.api;

import com.example.UserActionService.model.entity.Report;
import com.example.UserActionService.model.vo.ReportVo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface ReportRepository extends JpaRepository<Report, Long> {

    List<Report>  findByEmplId(int emplId);
    void deleteByEmplId(int emplId);
    boolean existsByReportDateAndEmplId(Date reportDate, int emplId);
}
