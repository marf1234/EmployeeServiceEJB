package com.innowise.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * The EmployeeDto class represents the DTO (Data Transfer Object) of an Employee entity.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmployeeDto {

    /**
     * The unique identifier of the Employee.
     */
    private Long id;
    /**
     * The name of the Employee.
     */
    private String name;
    /**
     * The surname of the Employee.
     */
    private String surname;
    /**
     * The unique identifier of the Department associated with the Employee.
     */
    private Long departmentId;
    /**
     * The salary of the Employee.
     */
    private BigDecimal salary;
}