package com.quinbay.EmployeeAttendaceSystem.model.vo;

import java.sql.Date;
import java.sql.Time;

public class ReportVo {

    private int reportId;
    public int emplId;
    public String reportDate;
    private Time checkIn;
    private Time checkOut;
    private int workingHours;
    private String actionType;
    public Date startDate;
    public Date endDate;
    public String day;
}
