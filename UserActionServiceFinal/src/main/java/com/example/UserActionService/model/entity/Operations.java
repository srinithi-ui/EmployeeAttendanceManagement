package com.example.UserActionService.model.entity;

import com.example.UserActionService.model.constant.FieldName;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;

@Entity
@Table(name=FieldName.OPERATION_T)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Operations {

    @Id
    @Column(name=FieldName.ACTION_ID)
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    public int actionId;

    @Column(name=FieldName.EMPLOYEE_ID)
    public int empId;


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

    public String getActionType() {
        return actionType;
    }

    public void setActionType(String actionType) {
        this.actionType = actionType;
    }



    @Column(name=FieldName.ACTION_TYPE)
    public String actionType;

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column(name=FieldName.CREATED_DATE)
    public Date createdDate;

    @Column(name=FieldName.STATUS)
    public String status;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @Column(name=FieldName.STATUS_UPDATED_DATE)
    public Date statusUpdatedDate;

    @Column(name=FieldName.ACTION_STARTED)
    @JsonFormat(pattern = "yyyy-MM-dd")
    public Date actionStarted;

    @Column(name=FieldName.ACTION_ENDED)
    @JsonFormat(pattern = "yyyy-MM-dd")
    public Date actionEnded;
}
