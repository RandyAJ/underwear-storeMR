package com.underwearstore.orderservice.dto;

public record ResponseDto(
    Long id,
    String username,
    String email,
    String role
) {}
