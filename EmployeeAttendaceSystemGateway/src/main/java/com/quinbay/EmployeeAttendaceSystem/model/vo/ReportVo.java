package com.quinbay.EmployeeAttendaceSystem.model.vo;

import java.sql.Date;
import java.sql.Time;

public class ReportVo {

    private int reportId;
    private int emplId;
    private Date reportDate;
    private Time checkIn;
    private Time checkOut;
    private int workingHours;
}
