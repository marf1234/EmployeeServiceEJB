package com.innowise.repository;

import com.innowise.entity.Authorization;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * The AuthorizationRepositoryImpl class implements the AuthorizationRepository interface
 * and provides methods for interacting with the authorization data stored in the database.
 */
@Stateless
@AllArgsConstructor
@NoArgsConstructor
public class AuthorizationRepositoryImpl implements AuthorizationRepository {
    @PersistenceContext(unitName = "EmployeeServicePersistenceProvider")
    private EntityManager entityManager;

    /**
     * Returns a list of all authorizations in the database.
     *
     * @return List of Authorization objects.
     */
    @Override
    public List<Authorization> findAll() {
        TypedQuery<Authorization> query = entityManager.createQuery("from Authorization", Authorization.class);
        return query.getResultList();
    }

    /**
     * Saves an authorization in the database.
     *
     * @param authority The Authorization object to save.
     * @return The saved Authorization object with its generated id.
     */
    @Override
    public Authorization save(Authorization authority) {
        Authorization newAuthority = entityManager.merge(authority);
        authority.setId(newAuthority.getId());
        return newAuthority;
    }

    /**
     * Finds an authorization by its id.
     *
     * @param id The id of the authorization to find.
     * @return The Authorization object with the given id or null if not found.
     */
    @Override
    public Authorization findById(Long id) {
        return entityManager.find(Authorization.class, id);
    }

    /**
     * Deletes an authorization by its id.
     *
     * @param id The id of the authorization to delete.
     */
    @Override
    public void deleteById(Long id) {
        Query query = entityManager.createQuery("delete from Authorization " + "where id =:authorityId");
        query.setParameter("authorityId", id);
        query.executeUpdate();
    }

    /**
     * Finds an authorization by its name.
     *
     * @param name The name of the authorization to find.
     * @return The Authorization object with the given name or null if not found.
     */
    @Override
    public Authorization findByName(String name) {
        Query query = entityManager.createQuery("SELECT u FROM Authority u WHERE u.name = :name");
        return (Authorization) query.getSingleResult();
    }
}
