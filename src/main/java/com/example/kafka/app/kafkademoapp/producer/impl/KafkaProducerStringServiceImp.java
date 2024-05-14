package com.example.kafka.app.kafkademoapp.producer.impl;

import com.example.kafka.app.kafkademoapp.producer.KafkaProducerService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class KafkaProducerStringServiceImp implements KafkaProducerService<String, String> {

    private final KafkaTemplate<String, String> stringKafkaTemplate;

    public KafkaProducerStringServiceImp(
            @Qualifier("kafkaStringTemplate") KafkaTemplate<String, String> stringKafkaTemplate) {
        this.stringKafkaTemplate = stringKafkaTemplate;
    }

    @Override
    public void sendMessage(String topicName, String message) {
        CompletableFuture<SendResult<String, String>> future = stringKafkaTemplate.send(topicName, message);
        future.whenComplete((result, ex) -> {
            if (ex == null) {
                System.out.println("Sent message=[" + message +
                        "] with offset=[" + result.getRecordMetadata().offset() + "]");
            } else {
                System.out.println("Unable to send message=[" +
                        message + "] due to : " + ex.getMessage());
            }
        });
    }
}
