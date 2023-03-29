package com.innowise.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;

import java.io.BufferedReader;
import java.io.IOException;

public class JsonConverter {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static <T> T convert(HttpServletRequest request, Class<T> clazz) throws IOException {
        BufferedReader reader = request.getReader();
        String json = "";
        String line;
        while ((line = reader.readLine()) != null) {
            json += line;
        }
        return objectMapper.readValue(json, clazz);
    }

    public static String toJson(Object obj) throws IOException {
        return objectMapper.writeValueAsString(obj);
    }
}
// public class EmployeeConverter {
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