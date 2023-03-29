package com.innowise.dto;

import com.innowise.entity.Authorization;
import com.innowise.entity.User;
import com.innowise.repository.AuthorizationRepository;
import jakarta.ejb.EJB;
import jakarta.ejb.Stateless;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.Optional;

@Stateless
@NoArgsConstructor
@AllArgsConstructor
public class UserConverter {

    @EJB
    private AuthorizationRepository authorizationRepository;

    public User toEntity(UserDto userDTO) {
        User user = User.builder()
                .id(userDTO.getId())
                .username(userDTO.getUsername())
                .password(userDTO.getPassword())
                .build();
        Optional.ofNullable(userDTO.getAuthorityId())
                .ifPresent(id -> {
                    Authorization au = authorizationRepository.findById(userDTO.getAuthorityId());
                    user.setAuthority(au);
                });

        return user;
    }

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
//@Stateless
//public class UserConverter {
//
//    @EJB
//    private AuthorizationRepository authorizationRepository;
//
//    public User toEntity(UserDto userDTO) {
//        return User.builder()
//                .id(userDTO.getId())
//                .username(userDTO.getUsername())
//                .password(userDTO.getPassword())
//                .authority(idToAuthority(userDTO.getAuthorityId()))
//                .build();
//    }
//
//    public UserDto toDTO(User user) {
//        return UserDto.builder()
//                .id(user.getId())
//                .username(user.getUsername())
//                .password(user.getPassword())
//                .authorityId(Optional.ofNullable(user.getAuthority()).map(Authorization::getId).orElse(null))
//                .build();
//    }
//
//    private Authorization idToAuthority(Long id) {
//        return id != null ? authorizationRepository.findById(id) : null;
//    }
//}