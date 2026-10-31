package com.underwearstore.orderservice.controller;

import com.underwearstore.orderservice.dto.ProductResponseDto;
import com.underwearstore.orderservice.dto.ResponseDto;
import com.underwearstore.orderservice.entity.Order;
import com.underwearstore.orderservice.repository.OrderRepository;
import com.underwearstore.orderservice.service.OrderService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
    private final OrderService orderService;
    private final OrderRepository orderRepository;

    public OrderController(OrderService orderService, OrderRepository orderRepository){
        this.orderService = orderService;
        this.orderRepository = orderRepository;
    }

    @GetMapping("/checkAvailability")
    public ProductResponseDto checkAvailability(@RequestParam Long id, @RequestParam Integer quantityOrdered){
		Order order = orderService.checkAvailability(id, quantityOrdered);
        return new ProductResponseDto(
				order.getId(),
				order.getProductId(),
				order.getProductName(),

				order.getPrice(),
				order.getSale(),
				order.getTotalPrice(),

				order.getQuantityOrdered(),
				order.getUserId()
		);
    }

    @GetMapping("/all")
    public List<Order> list(){
        return orderRepository.findAll();
    }
}
