package com.quinbay.EmployeeAttendaceSystem.services;

import com.quinbay.EmployeeAttendaceSystem.model.entity.Operations;
import com.quinbay.EmployeeAttendaceSystem.model.vo.*;

import java.util.List;

public interface Services {
    List<UserVo> getAllUserDetails();


    UserVo loginCheck(LoginVo loginVo);
    UserVo getAllUserDetailsByName(String userName);
    List<UserVo> getAllUserDetailsByManagerId(int managerId);

    List<SwipeHistoryVo> getUserSwipehistory(int id);
    String userSwipeDetails(SwipeHistoryVo swipeHistoryVo);

    String applyUserActions(int id, Operations operations);
    String applyActionStatus( Operations operation);

    List<OperationsVo> getUserActionhistory(int id);
    List<OperationsVo> viewEmployeePendingStatus(int id);
    List<ReportVo> reportGeneration(ReportVo reportVo);


}
