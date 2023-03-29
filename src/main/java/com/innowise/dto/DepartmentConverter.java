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

    public Department toEntity(DepartmentDto departmentDTO) {
        Department department = Department.builder()
                .name(departmentDTO.getName())
                .id(departmentDTO.getId())
                .build();

        Optional.ofNullable(departmentDTO.getEmployeeIds())
                .ifPresent(employeeIds -> employeeIds.forEach(id -> {
                    department.addEmployee(employeeRepository.findById(id)
//                            .orElseThrow(() -> new NoSuchRecordException
//                                    (String.format("Employee with id=%s not found", id)))
                    );
                }));

        return department;
    }

    public DepartmentDto toDTO(Department department) {
        DepartmentDto departmentDTO = DepartmentDto.builder()
                .name(department.getName())
                .id(department.getId())
                .build();

        Optional.ofNullable(department.getEmployees())
                .ifPresent(employees -> employees.forEach(employee -> departmentDTO.addEmployeeId(employee.getId())));

        return departmentDTO;
    }
}
//public class EmployeeConverter {
//    private DepartmentRepository departmentRepository;
//
//    public Employee convertToEntity(EmployeeDTO employeeDTO) {
//        Employee employee = Employee.builder()
//                .id(employeeDTO.getId())
//                .name(employeeDTO.getName())
//                .surname(employeeDTO.getSurname())
//                .salary(employeeDTO.getSalary())
//                .build();
//
//        Optional.ofNullable(employeeDTO.getDepartmentId())
//                .map(departmentRepository::findById)
//                .ifPresent(employee::setDepartment);
//
//        return employee;
//    }
//
//    public void updateEmployeeFields(EmployeeDTO employeeDTO, Employee employee) {
//        employee.setName(employeeDTO.getName());
//        employee.setSurname(employeeDTO.getSurname());
//        employee.setSalary(employeeDTO.getSalary());
//        if (employeeDTO.getDepartmentId() != null) {
//            departmentRepository.findById(employeeDTO.getDepartmentId())
//                    .ifPresent(employee::setDepartment);
//        } else {
//            employee.setDepartment(null);
//        }
//    }
//
//    public EmployeeDTO convertToDTO(Employee employee) {
//        EmployeeDTO employeeDTO = EmployeeDTO.builder()
//                .id(employee.getId())
//                .name(employee.getName())
//                .surname(employee.getSurname())
//                .salary(employee.getSalary())
//                .build();
//
//        Optional.ofNullable(employee.getDepartment())
//                .map(Department::getId)
//                .ifPresent(employeeDTO::setDepartmentId);
//
//        return employeeDTO;
//    }
//}