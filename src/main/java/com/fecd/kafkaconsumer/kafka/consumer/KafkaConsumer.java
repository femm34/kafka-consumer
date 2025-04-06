package com.fecd.kafkaconsumer.kafka.consumer;

import com.fecd.kafkaconsumer.kafka.KafkaProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;

@EnableKafka
@Configuration
@RequiredArgsConstructor
public class KafkaConsumer {
    private final KafkaProperties kafkaProperties;
}
