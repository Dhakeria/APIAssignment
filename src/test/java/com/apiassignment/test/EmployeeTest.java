package com.apiassignment.test;

import com.apiassignment.base.Groups;
import com.apiassignment.base.Services;
import com.apiassignment.clients.EmployeeClient;
import com.apiassignment.employee.create.CreateEmployeeResponse;
import com.apiassignment.employee.retrieveEmployeeDetail.RetrieveEmployeeResponse;
import com.apiassignment.utility.DataGenerator;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class EmployeeTest {
    EmployeeClient employeeClient;
    DataGenerator dataGenerator;
    CreateEmployeeResponse createResponse;
    Services services;


    public EmployeeTest() throws IOException {
        employeeClient = new EmployeeClient();
        dataGenerator = new DataGenerator();
        services = new Services();
    }

    @Test(groups = Groups.SMOKE, description = "Verify creation of employee details and retrieving it thereafter")
    public void createEmployeeAndRetrieve() {

        //Employee details creation
        createResponse = employeeClient.createEmployee(services.createEmployeeService(), dataGenerator.name(), dataGenerator.age(),
                dataGenerator.salary());
        Assert.assertTrue(createResponse.getStatus().equalsIgnoreCase("success"), "Employee Creation Failed");


        //Retrieval of Employee Details By Id
        RetrieveEmployeeResponse retrieveEmployeeResponse = employeeClient.retrieveEmployeeDetails
                (services.retrieveEmployeeDetailsService(), String.valueOf(createResponse.getData().getId()));
        Assert.assertEquals(retrieveEmployeeResponse.getData().getEmployee_name(),
                createResponse.getData().getName(), "Name Mismatch");
        Assert.assertEquals(retrieveEmployeeResponse.getData().getEmployee_age(),
                createResponse.getData().getAge(), "Age mismatch");
        Assert.assertEquals(retrieveEmployeeResponse.getData().getId(), createResponse.getData().getId(), "Id Mismatch");
        Assert.assertEquals(retrieveEmployeeResponse.getData().getEmployee_salary(),
                createResponse.getData().getSalary(), "Salary mismatch");

    }
}
