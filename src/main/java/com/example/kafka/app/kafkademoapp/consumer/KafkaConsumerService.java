package com.example.kafka.app.kafkademoapp.consumer;

public interface KafkaConsumerService <String, T> {

    void listenMessage(T message);

}
