package com.fecd.kafkaconsumer.kafka.listener;

import com.fecd.kafkaproducer.kafka.models.Event;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class KafkaListenerConfig {

    @KafkaListener(topics = "CUSTOMERS", containerFactory = "kafkaListenerContainerFactory", groupId = "consumer-group")
    public void listen(Event<?> event) {
        log.info("Received Customer created event .... with Id={}, data={}",
                event.getId(),
                event.getData().toString());
    }
}
