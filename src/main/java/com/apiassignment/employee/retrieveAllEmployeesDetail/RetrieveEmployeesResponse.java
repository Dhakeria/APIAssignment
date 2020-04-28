package com.apiassignment.employee.retrieveAllEmployeesDetail;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class RetrieveEmployeesResponse {
    String status;
    List<Data> data;
}
