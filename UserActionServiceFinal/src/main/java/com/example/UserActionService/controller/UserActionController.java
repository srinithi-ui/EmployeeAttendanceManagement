package com.example.UserActionService.controller;

import com.example.UserActionService.model.entity.Operations;
import com.example.UserActionService.model.entity.SwipeHistory;
import com.example.UserActionService.model.vo.OperationsVo;
import com.example.UserActionService.model.vo.ReportVo;
import com.example.UserActionService.model.vo.SwipeHistoryVo;
import com.example.UserActionService.model.vo.UserVo;
import com.example.UserActionService.services.ActionServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/actions")

public class UserActionController {

    @Autowired
    ActionServices actionServices;

    @GetMapping("/viewswipehistory")
    public List<SwipeHistoryVo> getSwipeHistory(@RequestParam int id)
    {
        return actionServices.getUserSwipehistory(id);
    }

    @PostMapping("/swipecard")
     public String saveSwipeDetails(@RequestParam int empId)

    {
            return actionServices.userSwipeDetails(empId);
    }

    @PostMapping("/applyactions")
    public String applyAction(@RequestParam int id, @RequestBody Operations operation)
    {
        return actionServices.applyUserActions(id,operation);
    }

    @PutMapping("/approval")
    public String actionUpdate(@RequestParam int id, @RequestBody Operations operation)
    {

        return actionServices.applyActionStatus(id,operation);
    }

    @GetMapping("/viewactionhistory")
    public List<OperationsVo> getActionHistory(@RequestParam int id)
    {
        return actionServices.getUserActionhistory(id);
    }

    @GetMapping("/viewpendingstatus")
    public List<OperationsVo> viewPendingStatus(@RequestParam int id)
    {
        System.out.println(id);
        return actionServices.viewEmployeePendingStatus(id);
    }



    @PostMapping("/report")
    public String reportGeneration(@RequestParam int id){
        return actionServices.reportGeneration(id);
    }





}
