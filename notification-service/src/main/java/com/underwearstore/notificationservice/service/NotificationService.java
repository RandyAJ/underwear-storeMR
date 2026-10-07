package com.underwearstore.notificationservice.service;

import org.springframework.stereotype.Service;
import com.underwearstore.notificationservice.repository.NotificationRepository;
import com.underwearstore.notificationservice.entity.Notification;
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

//    public List<Notification> findAllByUserId(Long orderId){
//        return notificationRepository.findAllByUserId(orderId);
//    }
}
