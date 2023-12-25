package com.example.UserActionService.dao.api;

import com.example.UserActionService.model.entity.Report;
import com.example.UserActionService.model.vo.ReportVo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public interface ReportRepository extends JpaRepository<Report, Long> {

    @Query("SELECT r FROM Report r WHERE r.emplId = :emplId AND r.reportDate >= :startDate AND r.reportDate <= :endDate")
    List<Report> findByEmplId(@Param("emplId") int emplId,
                              @Param("startDate") Date startDate,
                              @Param("endDate") Date endDate);


    void deleteByEmplId(int emplId);
    boolean existsByReportDateAndEmplId(String reportDate, int emplId);
}
