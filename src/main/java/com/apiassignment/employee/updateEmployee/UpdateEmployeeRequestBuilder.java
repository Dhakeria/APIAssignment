package com.apiassignment.employee.updateEmployee;

public class UpdateEmployeeRequestBuilder {
    UpdateEmployeeRequest updateEmployeeRequest;

    public UpdateEmployeeRequestBuilder() {
        updateEmployeeRequest = new UpdateEmployeeRequest();
    }
    public UpdateEmployeeRequestBuilder updateEmployeeDetails(String Name, Integer Age, String Salary) {
        updateEmployeeRequest.setName(Name);
        updateEmployeeRequest.setAge(Age);
        updateEmployeeRequest.setSalary(Salary);
        return this;
    }
    public UpdateEmployeeRequest build(){
        return updateEmployeeRequest;
    }
}
