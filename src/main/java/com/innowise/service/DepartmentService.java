package com.innowise.service;

import com.innowise.dto.DepartmentDto;

import java.util.List;

public interface DepartmentService {
    DepartmentDto saveDepartment(DepartmentDto department);

    List<DepartmentDto> findAll();

    DepartmentDto findById(Long id);

    void deleteById(Long id);
}
