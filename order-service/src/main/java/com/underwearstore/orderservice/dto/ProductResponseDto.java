package com.underwearstore.orderservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import java.math.BigDecimal;

@Getter
@AllArgsConstructor
public class ProductResponseDto {
    private Long id;
	private Long productId;
	private String productName;

    private BigDecimal price;
    private Integer sale;
    private BigDecimal totalPrice;

    private Integer quantityOrdered;
	private Integer userId;
}
