package com.innowise.dto;

import com.innowise.entity.Department;
import com.innowise.entity.Employee;
import com.innowise.repository.DepartmentRepository;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.Optional;
@Stateless
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeConverter {

        @EJB
        private DepartmentRepository departmentRepository;

        public Employee toEntity(EmployeeDto employeeDTO) {
            Employee employee = Employee.builder()
                    .id(employeeDTO.getId())
                    .name(employeeDTO.getName())
                    .salary(employeeDTO.getSalary())
                    .build();

            Optional.ofNullable(employeeDTO.getDepartmentId())
                    .ifPresent(id -> {
                        Department department = departmentRepository.findById(employeeDTO.getDepartmentId())
//                            .orElseThrow(() -> new NoSuchRecordException
//                                    (String.format("Department with id=%s not found", employeeDTO.getDepartmentId()))
//                            )
                                ;
                        employee.setDepartment(department);
                    });

            return employee;
        }

        public void updateEmployeeFields(EmployeeDto employeeDTO, Employee employee) {
            employee.setName(employeeDTO.getName());
            employee.setSalary(employeeDTO.getSalary());
            if (employeeDTO.getDepartmentId() != null) {
                Department department = departmentRepository.findById(employeeDTO.getDepartmentId())
//                    .orElseThrow(() -> new NoSuchRecordException
//                            (String.format("Department with id=%s not found", employeeDTO.getDepartmentId()))
//                    )
                        ;
                employee.setDepartment(department);
            } else {
                employee.setDepartment(null);
            }
        }

        public EmployeeDto toDTO(Employee employee) {
            EmployeeDto employeeDTO = EmployeeDto.builder()
                    .id(employee.getId())
                    .name(employee.getName())
                    .salary(employee.getSalary())
                    .build();
            System.out.println(employeeDTO);
            Optional.ofNullable(employee.getDepartment())
                    .ifPresent(department -> employeeDTO.setDepartmentId(department.getId()));

            return employeeDTO;
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