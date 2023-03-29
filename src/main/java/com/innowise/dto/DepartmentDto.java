package com.innowise.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DepartmentDto {

    private Long id;

    private String name;

    private List<Long> employeeIds;

    public void addEmployeeId(Long id) {
        if (employeeIds == null) {
            employeeIds = new ArrayList<>();
        }
        employeeIds.add(id);
    }
    //public void addEmployeeId(Long id) {
    //    employeeIds = Optional.ofNullable(employeeIds)
    //                          .orElse(new ArrayList<>());
    //    employeeIds.add(id);
    //}
}