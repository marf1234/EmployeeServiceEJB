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

/**
 * This class implements the EmployeeRepository interface and provides
 * <p>
 * methods to interact with the employee table in the database.
 */
@Stateless
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeRepositoryImpl implements EmployeeRepository {

    /**
     * The entity manager used to manage entities.
     */
    @PersistenceContext(unitName = "EmployeeServicePersistenceProvider")
    private EntityManager entityManager;

    /**
     * Returns a list of all employees in the database.
     *
     * @return a list of all employees
     */
    @Override
    @Transactional
    public List<Employee> findAll() {
        TypedQuery<Employee> query = entityManager.createQuery("from Employee", Employee.class);
        return query.getResultList();
    }

    /**
     * Saves an employee to the database.
     *
     * @param employee the employee to be saved
     * @return the saved employee
     */
    @Override
    public Employee save(Employee employee) {
        Employee newEmployee = entityManager.merge(employee);
        employee.setId(newEmployee.getId());
        return newEmployee;
    }

    /**
     * Finds an employee in the database by their ID.
     *
     * @param id the ID of the employee to be found
     * @return the employee with the specified ID, or null if not found
     */
    @Override
    public Employee findById(Long id) {
        return entityManager.find(Employee.class, id);
    }

    /**
     * Deletes an employee from the database by their ID.
     *
     * @param id the ID of the employee to be deleted
     */
    @Override
    public void deleteById(Long id) {
        Query query = entityManager.createQuery("delete from Employee " + "where id =:employeeId");
        query.setParameter("employeeId", id);
        query.executeUpdate();
    }
}
