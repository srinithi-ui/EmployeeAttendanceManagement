package com.example.UserActionService.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SwipeHistoryVo {

    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public Time getSwipeTime() {
        return swipeTime;
    }

    public void setSwipeTime(Time swipeTime) {
        this.swipeTime = swipeTime;
    }

    public Date getSwipeDate() {
        return swipeDate;
    }

    public void setSwipeDate(Date swipeDate) {
        this.swipeDate = swipeDate;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    private int employeeId;
    private Date swipeDate;
    private Time swipeTime;


}
