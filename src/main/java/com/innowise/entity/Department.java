package com.innowise.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a department entity in the system.
 */
@Entity
@Table(name = "department")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Department {

    /**
     * The unique identifier of the department.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    /**
     * The name of the department.
     */
    @Column(name = "name")
    private String name;
    /**
     * The list of employees that belong to the department.
     */
    @OneToMany(mappedBy = "department", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    private List<Employee> employees;

    /**
     * Adds an employee to the department's list of employees and sets the department of the employee.
     *
     * @param employee the employee to be added to the department
     */
    public void addEmployee(Employee employee) {
        if (employees == null) {
            employees = new ArrayList<>();
        }
        employees.add(employee);
        employee.setDepartment(this);
    }
}