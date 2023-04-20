package com.innowise.service;

import com.innowise.dto.UserDto;
import com.innowise.dto.UserProfile;
import jakarta.ejb.Local;

import java.util.List;

@Local
public interface UserService {
    //    UserDetails loadUserByUsername(String username);
    UserDto addUser(UserDto user);

    void deleteById(Long id);

    List<UserProfile> findAll();

    UserProfile findById(Long id);

    UserDto findByUsername(String username);

}