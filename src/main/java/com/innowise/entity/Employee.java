package com.innowise.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.math.BigDecimal;

/**
 * Employee entity represents an employee that works for a specific department.
 * <p>
 * It contains information such as name and salary, as well as a reference to its department.
 */
@Entity
@Table(name = "employees")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Employee {

    /**
     * Employee ID generated automatically.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    /**
     * Employee name.
     */
    @Column(name = "name")
    private String name;
    /**
     * Employee salary.
     */
    @Column(name = "salary")
    private BigDecimal salary;
    /**
     * Department the employee works for.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id", nullable = false)
    private Department department;
}