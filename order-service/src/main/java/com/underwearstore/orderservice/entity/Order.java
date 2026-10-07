package com.underwearstore.orderservice.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.AllArgsConstructor;
import java.math.BigDecimal;

@Entity
@Table(name = "orders")
@AllArgsConstructor
@Setter
@Getter
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @NotNull
    private Long productId;
    @NotNull
    private String productName;

    @NotNull
    private BigDecimal price;
    @NotNull
    private Integer sale;
    @NotNull
    private BigDecimal totalPrice;

    @NotNull
    private Integer quantityOrdered;
}
