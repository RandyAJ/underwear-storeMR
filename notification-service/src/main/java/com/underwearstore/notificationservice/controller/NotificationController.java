package com.underwearstore.notificationservice.controller;

import com.underwearstore.notificationservice.entity.Notification;
import com.underwearstore.notificationservice.service.NotificationService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class NotificationController {
    private final NotificationService notificationService;

    public NotificationController(NotificationService notificationService){
        this.notificationService = notificationService;
    }

    @GetMapping("/all") // вывод всей таблицы заказов
    public List<Notification> list(){
        return notificationService.list();
    }

    @GetMapping
    public List<Notification> findAllByOrderId(@RequestParam Long orderId){
        return notificationService.findAllByOrderId(orderId);
    }

//    @GetMapping
//    public List<Notification> findAllByUserId(@RequestParam Long userId){
//        return notificationService.findAllByUserId(userId);
//    }
}
