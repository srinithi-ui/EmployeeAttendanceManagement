package com.quinbay.EmployeeAttendaceSystem.services;

import com.quinbay.EmployeeAttendaceSystem.model.entity.Operations;
import com.quinbay.EmployeeAttendaceSystem.model.vo.LoginVo;
import com.quinbay.EmployeeAttendaceSystem.model.vo.OperationsVo;
import com.quinbay.EmployeeAttendaceSystem.model.vo.SwipeHistoryVo;
import com.quinbay.EmployeeAttendaceSystem.model.vo.UserVo;

import java.util.List;

public interface Services {
    List<UserVo> getAllUserDetails();


    UserVo loginCheck(LoginVo loginVo);
    UserVo getAllUserDetailsByName(String userName);
    List<UserVo> getAllUserDetailsByManagerId(int managerId);

    List<SwipeHistoryVo> getUserSwipehistory(int id);
    String userSwipeDetails(SwipeHistoryVo swipeHistoryVo);

    String applyUserActions(int id, Operations operations);
    String applyActionStatus(int id, Operations operation);

    List<OperationsVo> getUserActionhistory(int id);
    List<OperationsVo> viewEmployeePendingStatus(int id);
    String reportGeneration(int id);


}
