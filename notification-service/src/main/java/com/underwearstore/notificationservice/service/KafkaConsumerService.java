package com.underwearstore.notificationservice.service;

import com.underwearstore.notificationservice.dto.OrderCreatedEvent;
import com.underwearstore.notificationservice.entity.Notification;
import com.underwearstore.notificationservice.repository.NotificationRepository;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerService {

    private final NotificationRepository notificationRepository;

    public KafkaConsumerService(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    @KafkaListener(topics = "orders", groupId = "notification-service")
    public void consume(OrderCreatedEvent event) {

        Notification notification = new Notification();

        notification.setOrderId(event.getOrderId());
        notification.setProductId(event.getProductId());
        notification.setProductName(event.getProductName());
        notification.setQuantityOrdered(event.getQuantityOrdered());
        notification.setPrice(event.getPrice());
        notification.setSale(event.getSale());
        notification.setTotalPrice(event.getTotalPrice());

        notificationRepository.save(notification);

    }
}