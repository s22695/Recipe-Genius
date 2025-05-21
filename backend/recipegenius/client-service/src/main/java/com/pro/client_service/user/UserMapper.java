package com.pro.client_service.user;

import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public User toUser(UserRequest request) {
        if (request == null) {
            return null;
        }
        return User.builder()
                .id(request.id())
                .firstName(request.firstname())
                .lastName(request.lastname())
                .email(request.email())
                .build();
    }

    public UserResponse fromUser(User user) {
        if (user == null) {
            return null;
        }
        return new UserResponse(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail()
        );
    }
}
