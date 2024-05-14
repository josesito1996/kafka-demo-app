package com.example.kafka.app.kafkademoapp.web;

import com.example.kafka.app.kafkademoapp.model.Message;
import com.example.kafka.app.kafkademoapp.producer.KafkaProducerService;
import com.example.kafka.app.kafkademoapp.producer.impl.KafkaProducerMessageServiceImp;
import com.example.kafka.app.kafkademoapp.producer.impl.KafkaProducerStringServiceImp;

import org.springframework.web.bind.annotation.*;

@RequestMapping("/api-kafka-producer")
@RestController
public class KafkaRestController {

    private final KafkaProducerService<String, Message> kafkaMessageProducer;

    private final KafkaProducerService<String, String> kafkaStringProducer;

    public KafkaRestController(KafkaProducerMessageServiceImp kafkaMessageProducer,
                               KafkaProducerStringServiceImp kafkaStringProducer) {
        this.kafkaMessageProducer = kafkaMessageProducer;
        this.kafkaStringProducer = kafkaStringProducer;

    }

    @GetMapping("/sendMessage/{message}")
    public String sendMessage(@PathVariable String message) {
        kafkaStringProducer.sendMessage("topic1", message);
        return "Mensaje enviado: " + message;
    }

    @PostMapping("/buildMessage")
    public Message sendMessage(@RequestBody Message message) {
        kafkaMessageProducer.sendMessage("topic1", message);
        return message;
    }


}
