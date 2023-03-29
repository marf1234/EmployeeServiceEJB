package com.innowise.repository;

import com.innowise.entity.Department;

import java.util.List;

public interface DepartmentRepository {
    List<Department> findAll();

    Department save(Department department);

    Department findById(Long id);

    void deleteById(Long id);
}
