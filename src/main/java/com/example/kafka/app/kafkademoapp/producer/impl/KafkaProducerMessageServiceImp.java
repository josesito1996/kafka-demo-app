package com.example.kafka.app.kafkademoapp.producer.impl;

import com.example.kafka.app.kafkademoapp.model.Message;
import com.example.kafka.app.kafkademoapp.producer.KafkaProducerService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class KafkaProducerMessageServiceImp implements KafkaProducerService<String, Message> {

    private final KafkaTemplate<String, Message> messageKafkaTemplate;

    public KafkaProducerMessageServiceImp(
            @Qualifier("kafkaMessageTemplate") KafkaTemplate<String, Message> messageKafkaTemplate) {
        this.messageKafkaTemplate = messageKafkaTemplate;
    }

    @Override
    public void sendMessage(String topicName, Message message) {
        CompletableFuture<SendResult<String, Message>> future = messageKafkaTemplate.send(topicName, message);
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
