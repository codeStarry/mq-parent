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
@RequestMapping(value = "/fanout")
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class RabbitFanoutController {

    private final RabbitTemplate rabbitTemplate;

    @RequestMapping(value = "/send/msg")
    public String manSendMessage(String message) {
        Map<String, Object> map = new HashMap<>(4);
        String messageId = String.valueOf(UUID.randomUUID());
        String createTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        map.put("messageId", messageId);
        map.put("createTime", createTime);
        map.put("message", message);

        rabbitTemplate.convertAndSend(Constants.FANOUT_EXCHANGE_KEY, null, map);

        return "ok";
    }
}
