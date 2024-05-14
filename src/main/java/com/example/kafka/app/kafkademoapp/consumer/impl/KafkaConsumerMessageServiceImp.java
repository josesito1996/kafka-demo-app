package com.example.kafka.app.kafkademoapp.consumer.impl;

import com.example.kafka.app.kafkademoapp.consumer.KafkaConsumerService;
import com.example.kafka.app.kafkademoapp.model.Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class KafkaConsumerMessageServiceImp implements KafkaConsumerService<String, Message>{


    @Override
    @KafkaListener(topics = "${spring.kafka.consumer.topic-name}", groupId = "${spring.kafka.consumer.group-id}")
    public void listenMessage(Message message) {
        log.info("KafkaConsumerMessageServiceImp.listenMessage : {}", message);
    }

}
