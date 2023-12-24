package com.quinbay.EmployeeAttendaceSystem.servicesImplementation;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.quinbay.EmployeeAttendaceSystem.model.entity.Operations;
import com.quinbay.EmployeeAttendaceSystem.model.entity.User;
import com.quinbay.EmployeeAttendaceSystem.model.vo.LoginVo;
import com.quinbay.EmployeeAttendaceSystem.model.vo.OperationsVo;
import com.quinbay.EmployeeAttendaceSystem.model.vo.SwipeHistoryVo;
import com.quinbay.EmployeeAttendaceSystem.model.vo.UserVo;
import com.quinbay.EmployeeAttendaceSystem.services.Services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Arrays;
import java.util.List;

@Service
public class ServicesImpl implements Services{
    @Autowired
    RestTemplate restTemplate;
    String baseUrl = "http://localhost:8080/userService";
    String baseUrlAction = "http://localhost:8094/actions";
    public List<UserVo> getAllUserDetails(){
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<>(headers);
        UriComponents builder = UriComponentsBuilder.fromHttpUrl(baseUrl+"/user").build();
        return restTemplate.exchange(builder.toUriString(), HttpMethod.GET, entity, List.class).getBody();

    }

    public UserVo loginCheck(LoginVo loginVo) {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<LoginVo> entity = new HttpEntity<>(loginVo, headers);
        UriComponents builder = UriComponentsBuilder.fromHttpUrl(baseUrl+"/user/login").build();

        return restTemplate.exchange(builder.toUriString(), HttpMethod.POST, entity, UserVo.class).getBody();

    }

    public UserVo getAllUserDetailsByName(String userName){
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<>(headers);

        UriComponents builder = UriComponentsBuilder.fromHttpUrl(baseUrl+"/user/name").queryParam("userName",userName).build();

        return restTemplate.exchange(builder.toUriString(), HttpMethod.GET, entity,UserVo.class).getBody();


    }
    public List<UserVo> getAllUserDetailsByManagerId(int managerId){
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<>(headers);

        UriComponents builder = UriComponentsBuilder.fromHttpUrl(baseUrl+"/user/manager").queryParam("managerId",managerId).build();

        return restTemplate.exchange(builder.toUriString(), HttpMethod.GET, entity,List.class).getBody();

    }

    public List<SwipeHistoryVo> getUserSwipehistory(int id){
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<>(headers);

        UriComponents builder = UriComponentsBuilder.fromHttpUrl(baseUrlAction+"/viewswipehistory").queryParam("id",id).build();

        return restTemplate.exchange(builder.toUriString(), HttpMethod.GET, entity,List.class).getBody();

    }

    public String userSwipeDetails(SwipeHistoryVo swipeHistoryVo) {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<>(headers);
        UriComponents builder = UriComponentsBuilder.fromHttpUrl(baseUrlAction+"/swipecard").queryParam("empId",swipeHistoryVo.getEmployeeId()).build();

        return restTemplate.exchange(builder.toUriString(), HttpMethod.POST, entity, String.class).getBody();

    }

    public String applyUserActions(int id, Operations operations) {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Operations> entity = new HttpEntity<>(operations, headers);
        UriComponents builder = UriComponentsBuilder.fromHttpUrl(baseUrlAction+"/applyactions").queryParam("id",id).build();

        return restTemplate.exchange(builder.toUriString(), HttpMethod.POST, entity, String.class).getBody();

    }

    public String applyActionStatus(int id, Operations operations) {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Operations> entity = new HttpEntity<>(operations, headers);
        UriComponents builder = UriComponentsBuilder.fromHttpUrl(baseUrlAction+"/approval").queryParam("id",id).build();

        return restTemplate.exchange(builder.toUriString(), HttpMethod.PUT, entity, String.class).getBody();

    }

    public List<OperationsVo> getUserActionhistory(int id){
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<>(headers);

        UriComponents builder = UriComponentsBuilder.fromHttpUrl(baseUrlAction+"/viewactionhistory").queryParam("id",id).build();

        return restTemplate.exchange(builder.toUriString(), HttpMethod.GET, entity,List.class).getBody();


    }
    public List<OperationsVo> viewEmployeePendingStatus(int id){
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<>(headers);

        UriComponents builder = UriComponentsBuilder.fromHttpUrl(baseUrlAction+"/viewpendingstatus").queryParam("id",id).build();

        return restTemplate.exchange(builder.toUriString(), HttpMethod.GET, entity,List.class).getBody();


    }
    public String reportGeneration(int id) {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Operations> entity = new HttpEntity<>(headers);
        UriComponents builder = UriComponentsBuilder.fromHttpUrl(baseUrlAction+"/report").queryParam("id",id).build();

        return restTemplate.exchange(builder.toUriString(), HttpMethod.POST, entity, String.class).getBody();

    }




}
