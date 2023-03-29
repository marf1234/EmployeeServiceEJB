package com.innowise.repository;

import com.innowise.entity.Employee;

import java.util.List;

public interface EmployeeRepository {
    List<Employee> findAll();

    Employee save(Employee employee);

    Employee findById(Long id);

    void deleteById(Long id);
}

