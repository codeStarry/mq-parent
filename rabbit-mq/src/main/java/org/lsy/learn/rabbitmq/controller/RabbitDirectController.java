package org.lsy.learn.rabbitmq.controller;

import lombok.RequiredArgsConstructor;
import org.lsy.learn.rabbitmq.config.Constants;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping(value = "/direct")
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class RabbitDirectController {

    private final RabbitTemplate rabbitTemplate;

    @RequestMapping(value = "/send/message")
    public String sendMessage(String message) {
        Map<String, Object> msgMap = new HashMap<>(3);
        String messageId = String.valueOf(UUID.randomUUID());
        String createTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        msgMap.put("messageId", messageId);
        msgMap.put("createTime", createTime);
        msgMap.put("message", message);

        rabbitTemplate.convertAndSend(Constants.DIRECT_EXCHANGE_KEY, Constants.DIRECT_ROUTING_KEY, msgMap);

        return "ok";
    }
}
