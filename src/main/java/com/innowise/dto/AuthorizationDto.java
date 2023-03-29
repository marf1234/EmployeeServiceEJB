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

    private Long id;

    private String name;

    private List<UserDto> users;

    public void addUser(UserDto user) {
        if (users == null) {
            users = new ArrayList<>();
        }
        users.add(user);
    }
}
//@Data
//@AllArgsConstructor
//@Builder
//public class AuthorizationDto {
//
//    private Long id;
//
//    private String name;
//
//    private List<UserDto> users = new ArrayList<>();
//
//    public void addUser(UserDto user) {
//        this.users.add(user);
//    }
//}