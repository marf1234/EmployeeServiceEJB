package com.innowise.dto;

import com.innowise.entity.User;
import jakarta.ejb.Stateless;

@Stateless
public class UserProfileConverter {

    public UserProfile toDTO(User user) {
        UserProfile userCard = UserProfile.builder()
                .id(user.getId())
                .username(user.getUsername())
                .authorityId(user.getAuthority().getId())
                .build();
        return userCard;
    }
}
//@Stateless
//public class UserCardConverter {
//
//    public UserProfile toDTO(User user) {
//        return UserProfile.builder()
//                .id(user.getId())
//                .username(user.getUsername())
//                .authorityId(user.getAuthority().getId())
//                .build();
//    }
//}