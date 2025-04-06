package com.fecd.kafkaconsumer.kafka;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
public class KafkaProperties {
    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapServers;
}
