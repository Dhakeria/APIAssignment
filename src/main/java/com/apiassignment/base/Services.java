package com.apiassignment.base;

import java.io.IOException;

public class Services {
    static BaseClass  baseClass;
    static String baseURl;
    public Services() throws IOException {
        baseClass = new BaseClass();
        baseURl = baseClass.getEnvironmentConfig();
    }

    public String createEmployeeService(){
        return baseURl + "/api/v1/create";
    }

    public String retrieveEmployeeDetailsService(){
        return baseURl + "/api/v1/employee/";
    }

    public String retrieveAllEmployeesDetailsService(){
        return baseURl + "/api/v1/employees";
    }

    public String updateEmployeeService(){
        return baseURl + "/api/v1/update/";
    }
}
