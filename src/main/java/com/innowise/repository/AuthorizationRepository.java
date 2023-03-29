package com.innowise.repository;

import com.innowise.entity.Authorization;

import java.util.List;

public interface AuthorizationRepository {
    List<Authorization> findAll();

    Authorization save(Authorization authority);

    Authorization findById(Long id);

    void deleteById(Long id);

    Authorization findByName(String username);

}
//public interface AuthorizationRepository {
//    List<Authorization> findAll();
//
//    Authorization findById(Long id);
//
//    Authorization save(Authorization authorization);
//
//    void deleteById(Long id);
//
//    Optional<Authorization> findByName(String username);
//}