package com.underwearstore.orderservice.service;

import com.underwearstore.orderservice.dto.OrderCreatedEvent;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducerService {

    private final KafkaTemplate<String, OrderCreatedEvent> kafkaTemplate;

    public KafkaProducerService(KafkaTemplate<String, OrderCreatedEvent> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(OrderCreatedEvent event) {
        // Отправляем сообщение в топик "orders"
        kafkaTemplate.send("orders", event)
                .whenComplete((result, ex) -> {
                    if (ex == null) {
                        System.out.println("Сообщение успешно отправлено. Смещение: " +
                                result.getRecordMetadata().offset());
                    } else {
                        System.err.println("Ошибка отправки: " + ex.getMessage());
                    }
                });
    }
}