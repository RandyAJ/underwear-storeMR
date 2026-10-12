package com.underwearstore.orderservice.dto;

public record RequestDto(
    String username,
    String email,
    String password
) {}
