package com.innowise.controller;

import com.innowise.dto.UserDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * A request object representing the data submitted when registering a new user.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RegistrationRequest {

    /**
     * The username of the new user.
     */
    private String username;

    /**
     * The password of the new user.
     */
    private String password;

    /**
     * The authority ID of the new user.
     */
    private Long authorityId;

    /**
     * Converts this registration request to a user DTO.
     *
     * @return the user DTO representation of this registration request
     */
    public UserDto toDTO() {
        return UserDto.builder().username(username).password(password).authorityId(authorityId).build();
    }
}
