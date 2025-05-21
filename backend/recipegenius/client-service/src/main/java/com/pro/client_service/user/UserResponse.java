package com.pro.client_service.user;

public record UserResponse(
        Long id,
        String firstname,
        String lastname,
        String email
) {

}
