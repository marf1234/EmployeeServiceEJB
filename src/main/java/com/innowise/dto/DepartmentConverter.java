package com.innowise.dto;

import com.innowise.entity.Department;
import com.innowise.repository.EmployeeRepository;
import jakarta.ejb.Stateless;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.Optional;

@Stateless
@AllArgsConstructor
@NoArgsConstructor
public class DepartmentConverter {

    private EmployeeRepository employeeRepository;

    /**
     * Converts a DepartmentDto to a Department entity.
     *
     * @param departmentDTO the DepartmentDto to be converted
     * @return the converted Department entity
     */
    public Department toEntity(DepartmentDto departmentDTO) {
        Department department = Department.builder().name(departmentDTO.getName()).id(departmentDTO.getId()).build();

        Optional.ofNullable(departmentDTO.getEmployeeIds()).ifPresent(employeeIds -> employeeIds.forEach(id -> {
            department.addEmployee(employeeRepository.findById(id));
        }));

        return department;
    }

    /**
     * Converts a Department entity to a DepartmentDto.
     *
     * @param department the Department entity to be converted
     * @return the converted DepartmentDto
     */
    public DepartmentDto toDTO(Department department) {
        DepartmentDto departmentDTO = DepartmentDto.builder().name(department.getName()).id(department.getId()).build();

        Optional.ofNullable(department.getEmployees()).ifPresent(employees -> employees.forEach(employee -> departmentDTO.addEmployeeId(employee.getId())));

        return departmentDTO;
    }
}
