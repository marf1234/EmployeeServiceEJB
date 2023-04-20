package com.innowise.dto;

import com.innowise.entity.Department;
import com.innowise.entity.Employee;
import com.innowise.repository.DepartmentRepository;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.Optional;

/**
 * The EmployeeConverter class is responsible for converting EmployeeDto objects to Employee entities and vice versa.
 */
@Stateless
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeConverter {

    @EJB
    private DepartmentRepository departmentRepository;

    /**
     * Converts an EmployeeDto object to an Employee entity.
     *
     * @param employeeDTO the EmployeeDto object to convert
     * @return an Employee entity representing the given EmployeeDto object
     */
    public Employee toEntity(EmployeeDto employeeDTO) {
        Employee employee = Employee.builder().id(employeeDTO.getId()).name(employeeDTO.getName()).salary(employeeDTO.getSalary()).build();

        Optional.ofNullable(employeeDTO.getDepartmentId()).ifPresent(id -> {
            Department department = departmentRepository.findById(employeeDTO.getDepartmentId());
            employee.setDepartment(department);
        });

        return employee;
    }

    /**
     * Updates the fields of an existing Employee entity with the values of an EmployeeDto object.
     *
     * @param employeeDTO the EmployeeDto object containing the new values for the Employee entity
     * @param employee    the existing Employee entity to update
     */
    public void updateEmployeeFields(EmployeeDto employeeDTO, Employee employee) {
        employee.setName(employeeDTO.getName());
        employee.setSalary(employeeDTO.getSalary());
        if (employeeDTO.getDepartmentId() != null) {
            Department department = departmentRepository.findById(employeeDTO.getDepartmentId());
            employee.setDepartment(department);
        } else {
            employee.setDepartment(null);
        }
    }

    /**
     * Converts an Employee entity to an EmployeeDto object.
     *
     * @param employee the Employee entity to convert
     * @return an EmployeeDto object representing the given Employee entity
     */
    public EmployeeDto toDTO(Employee employee) {
        EmployeeDto employeeDTO = EmployeeDto.builder().id(employee.getId()).name(employee.getName()).salary(employee.getSalary()).build();

        Optional.ofNullable(employee.getDepartment()).ifPresent(department -> employeeDTO.setDepartmentId(department.getId()));

        return employeeDTO;
    }
}