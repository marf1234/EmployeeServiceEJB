package com.innowise.repository;

import com.innowise.entity.User;

import java.util.List;

public interface UserRepository {
    List<User> findAll();

    User save(User user);

    User findById(Long id);

    void deleteById(Long id);

    User findByUsername(String username);
}

