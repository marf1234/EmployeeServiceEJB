package com.innowise.service;

import com.innowise.dto.UserProfile;
import jakarta.ejb.Local;

import java.util.List;

@Local
public interface UserService {


    void deleteById(Long id);

    List<UserProfile> findAll();

    UserProfile findById(Long id);

}