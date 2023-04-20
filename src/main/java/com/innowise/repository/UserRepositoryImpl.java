package com.innowise.repository;

import com.innowise.entity.User;
import jakarta.ejb.Stateless;
import jakarta.persistence.*;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Implementation of UserRepository interface for managing User entities
 */
@Stateless
@NoArgsConstructor
@AllArgsConstructor
public class UserRepositoryImpl implements UserRepository {

    @PersistenceContext(unitName = "EmployeeServicePersistenceProvider")
    private EntityManager entityManager;

    /**
     * Finds all User entities in the database
     *
     * @return a List of User entities
     */
    @Override
    @Transactional
    public List<User> findAll() {
        Query query = entityManager.createQuery("from User", User.class);
        return (List<User>) query.getResultList();
    }

    /**
     * Saves a User entity to the database
     *
     * @param user the User entity to save
     * @return the saved User entity
     */
    @Override
    public User save(User user) {
        User newUser = entityManager.merge(user);
        return newUser;
    }


    /**
     * Finds a User entity by its ID
     *
     * @param id the ID of the User entity to find
     * @return the found User entity or null if not found
     */
    @Override
    public User findById(Long id) {
        User user = entityManager.find(User.class, id);
        return user;
    }

    /**
     * Deletes a User entity from the database by its ID
     *
     * @param id the ID of the User entity to delete
     */
    @Override
    public void deleteById(Long id) {
        Query query = entityManager.createQuery("delete from User " + "where id =:userId");
        query.setParameter("userId", id);
        query.executeUpdate();
    }

    /**
     * Finds a User entity by its username
     *
     * @param username the username of the User entity to find
     * @return the found User entity or null if not found
     */
    @Override
    public User findByUsername(String username) {
        TypedQuery<User> query = entityManager.createQuery("SELECT u FROM User u WHERE u.username = :username", User.class);
        User user = null;
        try {
            if (query != null) {
                query.setParameter("username", username);
                user = query.getSingleResult();
            }
        } catch (NoResultException ignored) {

        }
        return user;
    }

}
