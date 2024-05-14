package com.example.kafka.app.kafkademoapp.consumer.impl;

import com.example.kafka.app.kafkademoapp.consumer.KafkaConsumerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class KafkaConsumerStringServiceImp implements KafkaConsumerService<String, String>{


    @Override
    @KafkaListener(topics = "${spring.kafka.consumer.topic-name}", groupId = "${spring.kafka.consumer.group-id}")
    public void listenMessage(String message) {
        log.info("KafkaConsumerStringServiceImp.listenMessage : {}", message);
    }

}
