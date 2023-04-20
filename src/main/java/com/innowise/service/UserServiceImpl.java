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
import java.util.stream.Collectors;

/**

 This class implements the UserService interface and provides functionality related to user management.

 It uses EJB to inject dependencies of UserConverter, UserRepository, and UserProfileConverter.
 */
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

    /**

     Adds a new user to the database.
     @param userDTO a UserDto object containing information of the new user
     @return a UserDto object representing the newly added user
     */
    @Override
    public UserDto addUser(UserDto userDTO) {
        User entityDTO = converter.toEntity(userDTO);
        User returnedEntity = userRepository.save(entityDTO);
        return converter.toDTO(returnedEntity);
    }
    /**

     Deletes a user from the database by id.
     @param id the id of the user to be deleted
     */
    @Override
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }
    /**

     Retrieves a list of all user profiles from the database.
     @return a List of UserProfile objects representing all users in the database
     */
    @Transactional
    public List<UserProfile> findAll() {
        return userRepository.findAll()
                .stream()
                .map(userProfileConverter::toDTO)
                .collect(Collectors.toList());
    }
    /**

     Retrieves a user profile from the database by id.
     @param id the id of the user to be retrieved
     @return a UserProfile object representing the user with the specified id
     */
    @Override
    public UserProfile findById(Long id) {
        return userProfileConverter.toDTO(userRepository.findById(id));
    }
    /**

     Retrieves a user from the database by username.
     @param username the username of the user to be retrieved
     @return a UserDto object representing the user with the specified username
     */
    @Override
    public UserDto findByUsername(String username) {
        User user = userRepository.findByUsername(username);
        return converter.toDTO(user);
    }
}