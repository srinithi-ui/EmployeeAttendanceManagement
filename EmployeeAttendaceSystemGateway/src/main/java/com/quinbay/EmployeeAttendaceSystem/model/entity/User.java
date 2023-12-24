package com.quinbay.EmployeeAttendaceSystem.model.entity;

import com.quinbay.EmployeeAttendaceSystem.model.constant.FieldNames;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

//import com.quinbay.UserService.model.entity.SwipeHistory;

@Entity
@Table(name = FieldNames.USER_T)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @Column(name  = FieldNames.ID)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name  = FieldNames.EMPLOYEE_NAME)
    private String employeeName;

    @Column(name  = FieldNames.EMAIL)
    private String email;
    @Column(name  = FieldNames.PHONENUM)
    private String phoneNum;
    @Column(name  = FieldNames.MANAGERID)
    private int managerId;
    @Column(name  = FieldNames.ROLE)
    private String role;




}
