package com.fourthBeam.doubleWrite.business;

import com.fourthBeam.api.BO.Employees;


import java.util.Map;

public interface EmployeesService extends IDomainService{
    int testUpate(String firstName, String lastName);

    int testUpate3(Employees employees);

    int testUpate4(Map<String, String> map);

}
