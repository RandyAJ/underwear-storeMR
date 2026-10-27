package com.underwearstore.orderservice.controller;

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

    @GetMapping
    public Order checkAvailability(@RequestParam Long id, @RequestParam Integer quantityOrdered){
        return orderService.checkAvailability(id, quantityOrdered);
    }

    @GetMapping("/all")
    public List<Order> list(){
        return orderRepository.findAll();
    }
}
