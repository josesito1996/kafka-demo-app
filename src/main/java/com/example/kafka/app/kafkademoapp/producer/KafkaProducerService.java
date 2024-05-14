package com.example.kafka.app.kafkademoapp.producer;

public interface KafkaProducerService <String, T> {

    void sendMessage(String topicName, T message);

}
