package com.quinbay.EmployeeAttendaceSystem.model.vo;

import lombok.Data;

@Data
public class UserVo {
    public int id;
    private String employeeName;
    private String email;
    private String phoneNum;
    private int managerId;
    private String role;
    private String message = "Success";

}
