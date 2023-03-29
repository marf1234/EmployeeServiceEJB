package com.innowise.repository;

import com.innowise.entity.Employee;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;

@Stateless
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeRepositoryImpl implements EmployeeRepository {

    @PersistenceContext(unitName = "EmployeeServicePersistenceProvider")
    private EntityManager entityManager;

    @Override
    @Transactional
    public List<Employee> findAll() {
        TypedQuery<Employee> query = entityManager.createQuery("from Employee", Employee.class);
        return query.getResultList();
    }

    @Override
    public Employee save(Employee employee) {
        Employee newEmployee = entityManager.merge(employee);
        employee.setId(newEmployee.getId());
        return newEmployee;
    }

    @Override
    public Employee findById(Long id) {
        return entityManager.find(Employee.class, id);
    }

    @Override
    public void deleteById(Long id) {
        Query query = entityManager.createQuery("delete from Employee " +
                "where id =:employeeId");
        query.setParameter("employeeId", id);
        query.executeUpdate();
    }
}
//@Stateless
//public class EmployeeRepositoryImpl implements EmployeeRepository {
//
//    @PersistenceContext(unitName = "EmployeeServicePersistenceProvider")
//    private EntityManager entityManager;
//
//    @Override
//    public List<Employee> findAll() {
//        return entityManager.createQuery("SELECT e FROM Employee e", Employee.class).getResultList();
//    }
//
//    @Override
//    public Employee save(Employee employee) {
//        entityManager.persist(employee);
//        return employee;
//    }
//
//    @Override
//    public Employee findById(Long id) {
//        return entityManager.find(Employee.class, id);
//    }
//
//    @Override
//    public void deleteById(Long id) {
//        Employee employee = entityManager.find(Employee.class, id);
//        if (employee != null) {
//            entityManager.remove(employee);
//        }
//    }
//}