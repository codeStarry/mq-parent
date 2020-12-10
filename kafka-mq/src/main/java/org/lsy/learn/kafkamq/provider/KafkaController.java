package org.lsy.learn.kafkamq.provider;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "test")
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class KafkaController {

    private final KafkaTemplate<String, String> kafkaTemplate;

    @RequestMapping(value = "/send/msg")
    public String sendMsg(String message) {
        kafkaTemplate.send("kafka-key", message);
        return "ok";
    }
}
