package com.innowise.service;

import com.innowise.dto.DepartmentConverter;
import com.innowise.dto.DepartmentDto;
import com.innowise.entity.Department;
import com.innowise.repository.DepartmentRepository;
import com.innowise.repository.EmployeeRepository;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

/**
 * This class implements the DepartmentService interface and provides CRUD operations for the Department entity
 */
@Stateless
@AllArgsConstructor
@NoArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {

    @EJB
    private DepartmentRepository departmentRepository;
    @EJB
    private EmployeeRepository employeeRepository;
    @EJB
    private DepartmentConverter converter;

    /**
     * Saves a department entity to the database.
     *
     * @param department the DepartmentDTO object to be saved.
     * @return the DepartmentDTO object that was saved.
     */
    @Override
    public DepartmentDto saveDepartment(DepartmentDto department) {
        return converter.toDTO(departmentRepository.save(converter.toEntity(department)));
    }

    /**
     * Finds all department entities in the database.
     *
     * @return a list of DepartmentDTO objects that represent all the departments in the database.
     */
    @Transactional
    public List<DepartmentDto> findAll() {
        return departmentRepository.findAll().stream().map(converter::toDTO).collect(Collectors.toList());
    }

    /**
     * Finds a department entity by its ID.
     *
     * @param id the ID of the department entity to be found.
     * @return the DepartmentDTO object that represents the department with the specified ID.
     */
    @Override
    public DepartmentDto findById(Long id) {
        Department department = departmentRepository.findById(id);

        return converter.toDTO(department);
    }

    /**
     * Deletes a department entity by its ID.
     *
     * @param id the ID of the department entity to be deleted.
     */
    @Override
    @Transactional
    public void deleteById(Long id) {
        departmentRepository.deleteById(id);
    }
}