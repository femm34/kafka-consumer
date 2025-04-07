package com.fecd.kafkaconsumer.kafka.consumer;

import com.fecd.kafkaconsumer.kafka.KafkaProperties;
import com.fecd.kafkaconsumer.kafka.models.Event;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.ErrorHandlingDeserializer;
import org.springframework.kafka.support.serializer.JsonDeserializer;


import java.util.HashMap;
import java.util.Map;

@EnableKafka
@Configuration
@RequiredArgsConstructor
public class KafkaConsumer {
    private final KafkaProperties kafkaProperties;

    public Map<String, Object> consumerConfig() {
        return Map.of(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaProperties.getBootstrapServers(),
                ConsumerConfig.GROUP_ID_CONFIG, "consumer-group",
                ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class,
                ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class,
                JsonDeserializer.VALUE_DEFAULT_TYPE, "com.fecd.kafkaproducer.kafka.models.Event",
                JsonDeserializer.TRUSTED_PACKAGES, "com.fecd.kafkaproducer.kafka.models",
                JsonDeserializer.REMOVE_TYPE_INFO_HEADERS, true);
    }


    @Bean
    public ConsumerFactory<String, Event<?>> consumerFactory() {
        final JsonDeserializer<Event<?>> jsonDeserializer = new JsonDeserializer<>(Event.class);
        return new DefaultKafkaConsumerFactory<>(consumerConfig(), new StringDeserializer(), jsonDeserializer);
    }


    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, Event<?>> kafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, Event<?>> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        return factory;
    }

}
