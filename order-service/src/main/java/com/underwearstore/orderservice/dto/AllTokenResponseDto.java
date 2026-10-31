package com.underwearstore.orderservice.dto;

public record AllTokenResponseDto(
		String accessToken,
		String refreshToken
) {}
