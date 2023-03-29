package com.innowise.service;

import com.innowise.dto.EmployeeDto;

import java.util.List;

public interface EmployeeService {
    List<EmployeeDto> findAll();

    EmployeeDto saveEmployee(EmployeeDto employee);

    EmployeeDto findById(Long id);

    void deleteById(Long id);
}
