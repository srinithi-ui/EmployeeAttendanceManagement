package com.example.UserActionService.model.vo;

import java.sql.Date;


public class OperationsVo {
    private int actionId;

    public int getActionId() {
        return actionId;
    }

    public void setActionId(int actionId) {
        this.actionId = actionId;
    }

    public int getEmpId() {
        return empId;
    }

    public void setEmpId(int empId) {
        this.empId = empId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getActionType() {
        return actionType;
    }

    public void setActionType(String actionType) {
        this.actionType = actionType;
    }



    private int empId;
    private String status;
    private String actionType;
    private Date createdDate;

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getStatusUpdatedDate() {
        return statusUpdatedDate;
    }

    public void setStatusUpdatedDate(Date statusUpdatedDate) {
        this.statusUpdatedDate = statusUpdatedDate;
    }

    public Date getActionStarted() {
        return actionStarted;
    }

    public void setActionStarted(Date actionStarted) {
        this.actionStarted = actionStarted;
    }

    public Date getActionEnded() {
        return actionEnded;
    }

    public void setActionEnded(Date actionEnded) {
        this.actionEnded = actionEnded;
    }

    private Date statusUpdatedDate;
    private Date actionStarted;
    private Date actionEnded;


    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    private String employeeName;
}
