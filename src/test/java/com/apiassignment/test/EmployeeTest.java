package com.apiassignment.test;

import com.apiassignment.base.Groups;
import com.apiassignment.base.Services;
import com.apiassignment.clients.EmployeeClient;
import com.apiassignment.employee.create.CreateEmployeeResponse;
import com.apiassignment.employee.retrieveAllEmployeesDetail.Data;
import com.apiassignment.employee.retrieveAllEmployeesDetail.RetrieveEmployeesResponse;
import com.apiassignment.employee.retrieveEmployeeDetail.RetrieveEmployeeResponse;
import com.apiassignment.employee.updateEmployee.UpdateEmployeeResponse;
import com.apiassignment.utility.DataGenerator;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;

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
    @Test(groups = Groups.REGRESSION, description = "Verify retrieval of all employees details")
    public void retrieveAllEmployeesDetails(){

        //Retrieval of All employees details
        RetrieveEmployeesResponse retrieveEmployeesResponse = employeeClient.retrieveAllEmployeesDetails(services.retrieveAllEmployeesDetailsService());
        Assert.assertEquals(retrieveEmployeesResponse.getStatus(),"success","Unable to retrieve employees details");
        List<Data> employeesDetails = retrieveEmployeesResponse.getData();
        for(Data employee : employeesDetails){
            Assert.assertTrue(!employee.getId().isEmpty(),"Employee Id cannot be blank");
            Assert.assertTrue(!employee.getEmployee_name().isEmpty(),"Employee name not present for Id "+employee.getId());
            Assert.assertTrue(!employee.getEmployee_age().isEmpty(),"Employee age not present for Id "+employee.getId());
        }
    }

    @Test(groups = Groups.FUNCTIONAL, description = "Verify updation of employee details")
    public void updateEmployeesDetails(){

        //Updation of employee detail by Id
        UpdateEmployeeResponse updateResponse = employeeClient.updateEmployee(services.updateEmployeeService(),"21",dataGenerator.name(),dataGenerator.age(),
                dataGenerator.salary());
        Assert.assertTrue(updateResponse.getStatus().equalsIgnoreCase("success"), "Employee Details Updating Failed");

        //Retrieval of Employee Details By Id
        RetrieveEmployeeResponse retrieveEmployeeResponse = employeeClient.retrieveEmployeeDetails
                (services.retrieveEmployeeDetailsService(),"21");
        Assert.assertEquals(retrieveEmployeeResponse.getData().getEmployee_name(),
                updateResponse.getData().getEmployee_name(),"Name Mismatch");
        Assert.assertEquals(retrieveEmployeeResponse.getData().getEmployee_age(),
                updateResponse.getData().getEmployee_age(),"Age mismatch");
        Assert.assertEquals(retrieveEmployeeResponse.getData().getEmployee_salary(),
                updateResponse.getData().getEmployee_salary(),"Salary mismatch");

    }
}
