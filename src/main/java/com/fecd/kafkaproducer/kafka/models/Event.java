package com.fecd.kafkaproducer.kafka.models;

import com.fecd.kafkaconsumer.kafka.models.EventType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Event <T> {
    private String id;
    private LocalDate date;
    private EventType type;
    private T data;
}