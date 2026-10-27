package com.underwearstore.orderservice.dto;

public record TokenResponseDto(
		String accessToken,
		String refreshToken
) {}
