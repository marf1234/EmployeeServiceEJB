package com.innowise.dto;

import com.innowise.entity.Authorization;
import com.innowise.entity.User;
import com.innowise.repository.AuthorizationRepository;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.Optional;

/**
 * Converter class for converting between User and UserDto instances.
 */
@Stateless
@NoArgsConstructor
@AllArgsConstructor
public class UserConverter {

    @EJB
    private AuthorizationRepository authorizationRepository;

    /**
     * Converts a UserDto instance to a User entity.
     *
     * @param userDto the UserDto instance to convert
     * @return the converted User entity
     */
    public User toEntity(UserDto userDto) {
        User user = User.builder()
                .id(userDto.getId())
                .username(userDto.getUsername())
                .password(userDto.getPassword())
                .build();
        Optional.ofNullable(userDto.getAuthorityId())
                .ifPresent(id -> {
                    Authorization au = authorizationRepository.findById(userDto.getAuthorityId());
                    user.setAuthority(au);
                });

        return user;
    }

    /**
     * Converts a User entity to a UserDto instance.
     *
     * @param user the User entity to convert
     * @return the converted UserDto instance
     */
    public UserDto toDTO(User user) {
        UserDto userDTO = UserDto.builder()
                .id(user.getId())
                .username(user.getUsername())
                .password(user.getPassword())
                .build();
        System.out.println(userDTO);
        Optional.ofNullable(user.getAuthority())
                .ifPresent(authority -> userDTO.setAuthorityId(authority.getId()));

        return userDTO;
    }
}
