package com.fourthBeam.mapper.oracle;

import com.fourthBeam.api.BO.Employees;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface EmployeesOracleMapper {
    List<Employees> getAll();

    int testUpate(String firstName, String lastName);

    int testUpate2(@Param("firstName") String firstName, @Param("lastName") String lastName);

    int testUpate3(Employees employees);

    int testUpate4(Map<String, String> map);
}
