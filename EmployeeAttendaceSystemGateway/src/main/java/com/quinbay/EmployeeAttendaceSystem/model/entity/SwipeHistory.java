package com.quinbay.EmployeeAttendaceSystem.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.quinbay.EmployeeAttendaceSystem.model.constant.FieldNames;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name=FieldNames.SWIPE_T)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SwipeHistory {

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEmpId() {
        return empId;
    }

    public void setEmpId(int empId) {
        this.empId = empId;
    }

    public Timestamp getSwipeTime() {
        return swipeTime;
    }

    public void setSwipeTime(Timestamp swipeTime) {
        this.swipeTime = swipeTime;
    }

    @Id
    @Column(name=FieldNames.SWIPE_ID)
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;

    @Column(name=FieldNames.EMP_ID,nullable=false)
    private int empId;


    @Column(name=FieldNames.SWIPE_TIME,nullable=false)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
    private Timestamp swipeTime;


}
