package com.underwearstore.notificationservice.service;

import com.underwearstore.notificationservice.dto.OrderCreatedEvent;
import org.springframework.stereotype.Service;
import com.underwearstore.notificationservice.repository.NotificationRepository;
import com.underwearstore.notificationservice.entity.Notification;

import java.math.BigDecimal;
import java.util.List;

@Service
public class NotificationService {
    private final NotificationRepository notificationRepository;

    public NotificationService(NotificationRepository notificationRepository){
        this.notificationRepository = notificationRepository;
    }

    public List<Notification> list(){
        return notificationRepository.findAll();
    }

    public List<Notification> findAllByOrderId(Long orderId){
        return notificationRepository.findAllByOrderId(orderId);
    }

	public void save(
			Long orderId, Long productId,
			String productName, BigDecimal price,Integer sale,
			BigDecimal totalPrice, Integer quantityOrdered, Integer userId){

		notificationRepository.save(
			new Notification(
				null, orderId, productId,
				productName, price, sale,
				totalPrice, quantityOrdered, userId
			)
		);
	}

    public List<Notification> findAllByUserId(Long orderId){
        return notificationRepository.findAllByUserId(orderId);
    }
}
