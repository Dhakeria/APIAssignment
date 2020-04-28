package com.apiassignment.clients;

import com.apiassignment.base.BaseClass;
import com.apiassignment.employee.create.CreateEmployeeRequestBuilder;
import com.apiassignment.employee.create.CreateEmployeeResponse;
import com.apiassignment.employee.retrieveEmployeeDetail.RetrieveEmployeeResponse;
import com.google.gson.Gson;
import io.restassured.response.Response;

import java.io.IOException;

public class EmployeeClient {
    Gson gson;
    EmployeeClient employeeClient;
    Response response;
    BaseClass baseClass = new BaseClass();

    public EmployeeClient() throws IOException {
        gson =  new Gson();
    }

    public CreateEmployeeResponse createEmployee(String url, String name, String age, String salary){
        CreateEmployeeRequestBuilder createEmployeeRequestBuilder = new CreateEmployeeRequestBuilder();
        String request = gson.toJson(createEmployeeRequestBuilder.employeeDetails(name,age,salary).build());
        response = baseClass.postRequest(request,url);

        return (CreateEmployeeResponse)gson.fromJson(response.asString(), CreateEmployeeResponse.class);
    }

    public RetrieveEmployeeResponse retrieveEmployeeDetails(String url, String employeeId){
        response = baseClass.getRequest(url+employeeId);
        return (RetrieveEmployeeResponse)gson.fromJson(response.asString(), RetrieveEmployeeResponse.class);
    }

}
