package com.innowise.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * A data transfer object representing a department.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DepartmentDto {

    /**
     * The ID of the department.
     */
    private Long id;
    /**
     * The name of the department.
     */
    private String name;
    /**
     * The IDs of the employees in the department.
     */
    private List<Long> employeeIds;

    /**
     * Adds an employee ID to the list of employee IDs for this department.
     *
     * @param id the ID of the employee to add
     */
    public void addEmployeeId(Long id) {
        if (employeeIds == null) {
            employeeIds = new ArrayList<>();
        }
        employeeIds.add(id);
    }
}