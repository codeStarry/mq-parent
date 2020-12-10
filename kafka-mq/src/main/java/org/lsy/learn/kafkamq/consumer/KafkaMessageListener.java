package org.lsy.learn.kafkamq.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaMessageListener {

    @KafkaListener(topics = {"kafka-key"})
    public void receive(String message) {
        System.out.println("kafka-key收到消息：" + message);
    }
}
