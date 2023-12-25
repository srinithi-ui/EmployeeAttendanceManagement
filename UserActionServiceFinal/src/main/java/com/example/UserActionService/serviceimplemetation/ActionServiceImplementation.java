package com.example.UserActionService.serviceimplemetation;

import com.example.UserActionService.dao.api.OperationRepository;
import com.example.UserActionService.dao.api.ReportRepository;
import com.example.UserActionService.dao.api.UserActionRepository;
import com.example.UserActionService.model.entity.Operations;
import com.example.UserActionService.model.entity.Report;
import com.example.UserActionService.model.entity.SwipeHistory;
import com.example.UserActionService.model.vo.OperationsVo;
import com.example.UserActionService.model.vo.ReportVo;
import com.example.UserActionService.model.vo.SwipeHistoryVo;
import com.example.UserActionService.model.vo.UserVo;
import com.example.UserActionService.services.ActionServices;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.*;

@Service("actionServices")
public class ActionServiceImplementation implements ActionServices {
    @Autowired
    RestTemplate restTemplate;

    @Autowired
    UserActionRepository userActionRepository;

    @Autowired
    OperationRepository operationRepository;

    @Autowired
    ReportRepository reportRepository;

    private String userServiceUrl="http://localhost:8080/userService";
    SimpleDateFormat dateFormatTime = new SimpleDateFormat("HH:mm:ss");
    SimpleDateFormat dateFormatDate = new SimpleDateFormat("yyyy-MM-dd");
    ObjectMapper objectMapper = new ObjectMapper();

    public List<SwipeHistory> getUserSwipehistory(int id) {

        List<SwipeHistory> swipeHistories=userActionRepository.findAllByEmployeeId(id);
        objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd"));
        objectMapper.setTimeZone(TimeZone.getTimeZone("UTC"));

//        List<SwipeHistoryVo> list = objectMapper.convertValue(swipeHistories, List.class);
        return swipeHistories;

    }


    public String applyUserActions(int id, Operations operation)
    {
        operation.setEmpId(id);
        operation.setCreatedDate(Date.valueOf(LocalDate.now()).toString());
        operation.setStatus("pending");
        operation.setStatusUpdatedDate(Date.valueOf(LocalDate.now()).toString());

        operationRepository.save(operation);
        return operation.getActionType()+" applied";

    }

    public String applyActionStatus( Operations operationDetails)
    {
        Operations operation= operationRepository.findByEmpIdAndActionTypeAndCreatedDateAndActionStarted(
                operationDetails.getEmpId(), operationDetails.getActionType(), operationDetails.getCreatedDate(), operationDetails.getActionStarted());

        operation.setStatus(operationDetails.getStatus());

        operation.setStatusUpdatedDate(Date.valueOf(LocalDate.now()).toString());
        System.out.println("ksc"+operation);

        operationRepository.save(operation);
        return "status updated";

    }

    public String userSwipeDetails(int empId)
    {
        SwipeHistory swipeHistory = new SwipeHistory();

        swipeHistory.setEmployeeId(empId);
        swipeHistory.setSwipeDate(Date.valueOf(LocalDate.now()).toString());
        swipeHistory.setSwipeTime(Time.valueOf(LocalTime.now()));
        userActionRepository.save(swipeHistory);
        return "Success";
    }

    public List<OperationsVo> getUserActionhistory(int id)
    {
        List<Operations> operation= operationRepository.findAllByEmpId(id);
        ObjectMapper objectMapper = new ObjectMapper();

        List<OperationsVo> list = objectMapper.convertValue(operation, List.class);
        return list;
    }


   public List<OperationsVo> viewEmployeePendingStatus(int id)
   {
       System.out.println("hi"+id);
       HttpHeaders headers = new HttpHeaders();
       headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
       HttpEntity<String> entity = new HttpEntity<>(headers);
       UriComponents builder = UriComponentsBuilder.fromHttpUrl(userServiceUrl+"/user/manager").queryParam("managerId",id).build();
       List<UserVo> employees=restTemplate.exchange(builder.toUriString(), HttpMethod.GET, entity,new ParameterizedTypeReference<List<UserVo>>(){}).getBody();
       List<Integer> empIds = new ArrayList<>();
       for (UserVo user : employees) {
           empIds.add(user.getId());
       }
       ObjectMapper objectMapper = new ObjectMapper();
       List<OperationsVo> empoloyeeStatus=objectMapper.convertValue(operationRepository.findPendingActionsByEmployeeIds(empIds),new TypeReference<List<OperationsVo>>() {});
       List<OperationsVo> employeeList = new ArrayList<>();
       for (OperationsVo operation : empoloyeeStatus) {
           for (UserVo employee : employees) {
               if (operation.getEmpId() == employee.getId()) {
                   operation.setEmployeeName(employee.getEmployeeName());
                   break;
               }
           }
           employeeList.add(operation);
       }
       return  employeeList;
   }

    Object[] row;

    public List<ReportVo> reportGeneration(ReportVo reportVo)
    {
        List<Object[]> entries = userActionRepository.getSwipeSummaryByEmployeeId(reportVo.emplId);
        List<Object[]> entriesForActions = operationRepository.findActionSummaryByEmpId(reportVo.emplId);

        for(Object[] row : entries){
            int eid = (int) row[0];

            String swipeDate = (String) row[1];

//            Long count = (long) row[2];
            Time min = (Time) row[3];
            Time max = (Time) row[4];


            Report report = new Report();
            report.setEmplId(eid);
            report.setCheckIn(min);
            report.setCheckOut(max);
            report.setReportDate(swipeDate);
            int diff = max.getHours() - min.getHours();
            report.setWorkingHours(diff);
            if(!reportRepository.existsByReportDateAndEmplId(report.reportDate, reportVo.emplId))
                reportRepository.save(report);
        }

        for(Object[] row : entriesForActions){
            int eid = (int) row[0];
            String actionType = (String) row[1];
            String startDateString = (String) row[2];
            String endDateString = (String) row[3];
            LocalDate startDate = LocalDate.parse(startDateString);
            LocalDate endDate = LocalDate.parse(endDateString);
            while (!startDate.isAfter(endDate)) {

                Report report = new Report();
                report.setEmplId(eid);
                report.setActionType(actionType);
                report.setReportDate(startDateString);
                if(!reportRepository.existsByReportDateAndEmplId(report.reportDate, reportVo.emplId))
                    reportRepository.save(report);

                startDate = startDate.plusDays(1);
            }

        }

        List<Report> reportListById = reportRepository.findByEmplId(reportVo.emplId, reportVo.startDate, reportVo.endDate);

        List<ReportVo> reportList = objectMapper.convertValue(reportListById, List.class);

        return reportList;


    }


}