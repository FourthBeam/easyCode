package com.fourthBeam.mapper;

import com.fourthBeam.api.BO.Employees;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
public interface EmployeesMapper {

    List<Employees> getAll();

}
