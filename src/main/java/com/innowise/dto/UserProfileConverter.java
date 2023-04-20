package com.innowise.dto;

import com.innowise.entity.User;
import jakarta.ejb.Stateless;

/**
 * Converts a User entity to a UserProfile DTO.
 */
@Stateless
public class UserProfileConverter {

    /**
     * Converts a User entity to a UserProfile DTO.
     *
     * @param user the User entity to be converted
     * @return the resulting UserProfile DTO
     */
    public UserProfile toDTO(User user) {
        return UserProfile.builder().id(user.getId()).username(user.getUsername()).
                authorityId(user.getAuthority().getId()).build();
    }
}
