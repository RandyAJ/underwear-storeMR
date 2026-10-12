package com.underwearstore.orderservice.dto;

public record ResponeDto(
    Long id,
    String username,
    String email,
    String role,
    String password
) {}
