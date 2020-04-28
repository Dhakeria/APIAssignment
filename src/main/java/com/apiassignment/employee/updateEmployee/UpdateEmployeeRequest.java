package com.apiassignment.employee.updateEmployee;

import lombok.Getter;
import lombok.Setter;

@Setter @Getter
public class UpdateEmployeeRequest {
    String Name;
    String Salary;
    Integer Age;
}
