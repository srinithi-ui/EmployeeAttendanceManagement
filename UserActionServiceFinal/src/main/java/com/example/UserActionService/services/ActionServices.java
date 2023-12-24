package com.example.UserActionService.services;

import com.example.UserActionService.model.entity.Operations;
import com.example.UserActionService.model.entity.SwipeHistory;
import com.example.UserActionService.model.vo.OperationsVo;
import com.example.UserActionService.model.vo.ReportVo;
import com.example.UserActionService.model.vo.SwipeHistoryVo;
import com.example.UserActionService.model.vo.UserVo;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface ActionServices {
    List<SwipeHistoryVo> getUserSwipehistory(int id);

    String applyUserActions(int id, Operations operations);

    String applyActionStatus(int id, Operations operation);

    String userSwipeDetails(int id);

    List<OperationsVo> getUserActionhistory(int id);

    List<OperationsVo> viewEmployeePendingStatus(int id);



    String reportGeneration(int id);


//    List<ReportVo> generateUserReport(int id);

//}
}
