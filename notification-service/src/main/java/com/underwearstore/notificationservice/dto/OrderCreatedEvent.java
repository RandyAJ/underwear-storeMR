package com.underwearstore.notificationservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderCreatedEvent {

    private Long orderId;
    private Long productId;
    private String productName;

    private BigDecimal price;
    private Integer sale;
    private BigDecimal totalPrice;

    private Integer quantityOrdered;
//    private Integer user_id;

}
