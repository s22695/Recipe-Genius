package com.pro.auth.DTOs;

public record CreateUserRequest(String username, String email, String password, String firstName, String lastName) {}
