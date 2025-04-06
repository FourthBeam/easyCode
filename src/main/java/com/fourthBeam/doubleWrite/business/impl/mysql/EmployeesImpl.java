package com.fourthBeam.doubleWrite.business.impl.mysql;

import com.fourthBeam.api.BO.Employees;
import com.fourthBeam.doubleWrite.business.EmployeesService;
import com.fourthBeam.mapper.mysql.EmployeesMapper;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class EmployeesImpl implements EmployeesService {

    private final EmployeesMapper employeesMapper;

    @Override
    public int testUpate(String firstName, String lastName) {
        return employeesMapper.testUpate(firstName, lastName);
    }

    @Override
    public int testUpate3(Employees employees) {
        return employeesMapper.testUpate3(employees);
    }

    @Override
    public int testUpate4(Map<String, String> map) {
        return employeesMapper.testUpate4(map);
    }
}
