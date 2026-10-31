package com.underwearstore.notificationservice.service;

import com.underwearstore.notificationservice.dto.OrderCreatedEvent;
import com.underwearstore.notificationservice.entity.Notification;
import com.underwearstore.notificationservice.repository.NotificationRepository;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerService {

    private final NotificationRepository notificationRepository;
	private final NotificationService notificationService;

    public KafkaConsumerService(NotificationRepository notificationRepository, NotificationService notificationService) {
        this.notificationRepository = notificationRepository;
		this.notificationService = notificationService;
    }

    @KafkaListener(topics = "orders", groupId = "notification-service")
    public void consume(OrderCreatedEvent event) {
		notificationService.save(
				event.getOrderId(), event.getProductId(), event.getProductName(),
				event.getPrice(), event.getSale(), event.getTotalPrice(),
				event.getQuantityOrdered(), event.getUserId()
		);
    }
}