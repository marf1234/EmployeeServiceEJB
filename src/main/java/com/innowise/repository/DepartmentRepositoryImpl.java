package com.innowise.repository;

import com.innowise.entity.Department;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * DepartmentRepositoryImpl class is an implementation of DepartmentRepository interface,
 * <p>
 * which is responsible for CRUD operations on Department entity.
 */
@Stateless
@NoArgsConstructor
@AllArgsConstructor
public class DepartmentRepositoryImpl implements DepartmentRepository {

    /**
     * The instance variable entityManager is an EntityManager object that is used to interact with the persistence context.
     */
    @PersistenceContext(unitName = "EmployeeServicePersistenceProvider")
    private EntityManager entityManager;

    /**
     * This method finds all Department entities.
     *
     * @return A list of Department objects.
     */
    @Override
    public List<Department> findAll() {
        Query query = entityManager.createQuery("from Department", Department.class);
        return (query.getResultList());
    }

    /**
     * This method saves a new Department entity to the database.
     *
     * @param department The Department object to be saved.
     * @return The saved Department object.
     */
    @Override
    public Department save(Department department) {
        Department newDepartment = entityManager.merge(department);
        department.setId(newDepartment.getId());
        return newDepartment;
    }

    /**
     * This method finds a Department entity by its id.
     *
     * @param id The id of the Department object to be found.
     * @return The found Department object.
     */
    @Override
    public Department findById(Long id) {
        return entityManager.find(Department.class, id);
    }

    /**
     * This method deletes a Department entity by its id.
     *
     * @param id The id of the Department object to be deleted.
     */
    @Override
    public void deleteById(Long id) {
        Query query = entityManager.createQuery("delete from Department " + "where id =:departmentId");
        query.setParameter("departmentId", id);
        query.executeUpdate();
    }
}