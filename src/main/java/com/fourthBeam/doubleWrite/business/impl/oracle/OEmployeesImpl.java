package com.fourthBeam.doubleWrite.business.impl.oracle;

import com.fourthBeam.api.BO.Employees;
import com.fourthBeam.doubleWrite.business.EmployeesService;
import com.fourthBeam.mapper.mysql.EmployeesMapper;
import com.fourthBeam.mapper.oracle.EmployeesOracleMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class OEmployeesImpl implements EmployeesService {

    private final EmployeesOracleMapper employeesOracleMapper;

    @Override
    public int testUpate(String firstName, String lastName) {
        return employeesOracleMapper.testUpate(firstName, lastName);
    }

    @Override
    public int testUpate3(Employees employees) {
        return employeesOracleMapper.testUpate3(employees);
    }

    @Override
    public int testUpate4(Map<String, String> map) {
        return employeesOracleMapper.testUpate4(map);
    }
}
