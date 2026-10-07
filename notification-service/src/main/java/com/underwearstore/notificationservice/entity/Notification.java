package com.underwearstore.notificationservice.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import java.math.BigDecimal;

@Entity
@Table(name = "orders")
@NoArgsConstructor
@Setter
@Getter
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE) // ID генерируется до Insert в БД. Можно использовать пакетную вставку (batching). Можно прикрутить контроль над генерацией ID
    private Long id;
    @NotNull
    private Long orderId;
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
//    @NotNull
//    private Long userId;
}
