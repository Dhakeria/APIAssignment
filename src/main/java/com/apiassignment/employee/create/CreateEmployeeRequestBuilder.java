package com.apiassignment.employee.create;

public class CreateEmployeeRequestBuilder {
    CreateEmployeeRequest createEmployeeRequest;

    public CreateEmployeeRequestBuilder(){
        createEmployeeRequest = new CreateEmployeeRequest();
    }

    public CreateEmployeeRequestBuilder employeeDetails(String Name,String Age, String Salary){
        createEmployeeRequest.setName(Name);
        createEmployeeRequest.setAge(Age);
        createEmployeeRequest.setSalary(Salary);
        return this;
    }

    public CreateEmployeeRequest build(){
        return createEmployeeRequest;
    }
}
