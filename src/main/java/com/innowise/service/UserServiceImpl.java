package com.innowise.service;


import com.innowise.dto.UserConverter;
import com.innowise.dto.UserDto;
import com.innowise.dto.UserProfile;
import com.innowise.dto.UserProfileConverter;
import com.innowise.entity.User;
import com.innowise.repository.UserRepository;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Stateless
@NoArgsConstructor
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    @EJB
    private UserConverter converter;
    @EJB
    private UserRepository userRepository;
    @EJB
    private UserProfileConverter userProfileConverter;

    @Override
    public UserDto addUser(UserDto userDTO) {
        User entityDTO = converter.toEntity(userDTO);
        User returnedEntity = userRepository.save(entityDTO);
        return converter.toDTO(returnedEntity);
    }

    @Override
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

    @Transactional
    public List<UserProfile> findAll() {
        return userRepository.findAll()
                .stream()
                .map(userProfileConverter::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public UserProfile findById(Long id) {
        return userProfileConverter.toDTO(userRepository.findById(id));
    }

    @Override
    public UserDto findByUsername(String username) {
        User user = userRepository.findByUsername(username);
        return converter.toDTO(user);
    }

}