package com.innowise.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuthorizationDto {
    /**
     * The unique ID of the authorization.
     */
    private Long id;

    /**
     * The name of the authorization.
     */
    private String name;

    /**
     * The list of UserDto instances associated with this authorization.
     */
    private List<UserDto> users;

    /**
     * Adds a UserDto instance to the list of users associated with this authorization.
     *
     * @param user the UserDto instance to be added
     */
    public void addUser(UserDto user) {
        if (users == null) {
            users = new ArrayList<>();
        }
        users.add(user);
    }
}
