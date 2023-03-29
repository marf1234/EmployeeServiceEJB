package com.innowise.entity;

import com.innowise.dto.UserDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Registration {

    private String username;

    private String password;

    private Long authorityId;

    public UserDto toDTO() {
        return UserDto.builder()
                .username(username)
                .password(password)
                .authorityId(authorityId)
                .build();
    }
}
//    public UserDTO toDto() {
//        UserDTO dto = new UserDTO();
//        dto.setUsername(username);
//        dto.setPassword(password);
//        dto.setAuthorityId(authorityId);
//        return dto;
//    }