package com.innowise.repository;

import com.innowise.entity.Authorization;

import java.util.List;

/**
 * This interface represents the Authorization Repository and provides methods to perform CRUD operations on Authorizations.
 */
public interface AuthorizationRepository {

    /**
     * Returns a list of all the Authorizations.
     *
     * @return a list of all the Authorizations.
     */
    List<Authorization> findAll();

    /**
     * Saves an Authorization in the repository.
     *
     * @param authority the Authorization to be saved.
     * @return the saved Authorization.
     */
    Authorization save(Authorization authority);

    /**
     * Finds an Authorization by its ID.
     *
     * @param id the ID of the Authorization to be found.
     * @return the Authorization with the given ID.
     */
    Authorization findById(Long id);

    /**
     * Deletes an Authorization by its ID.
     *
     * @param id the ID of the Authorization to be deleted.
     */
    void deleteById(Long id);

    /**
     * Finds an Authorization by its username.
     *
     * @param username the username of the Authorization to be found.
     * @return the Authorization with the given username.
     */
    Authorization findByName(String username);
}
