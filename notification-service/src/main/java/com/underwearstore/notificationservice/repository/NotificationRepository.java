package com.underwearstore.notificationservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.underwearstore.notificationservice.entity.Notification;

import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification, Long>{
    List<Notification> findAllByOrderId(Long orderId);

//    List<Notification> findAllByUserId(Long userId);
}
