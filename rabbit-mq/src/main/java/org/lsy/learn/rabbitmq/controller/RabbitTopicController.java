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

/**
 * 主题交换机控制器
 */
@RestController
@RequestMapping(value = "/topic")
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class RabbitTopicController {

    private final RabbitTemplate rabbitTemplate;

    /**
     * man队列
     * @param message
     * @return
     */
    @RequestMapping(value = "/man/send/msg")
    public String manSendMessage(String message) {
        Map<String, Object> map = new HashMap<>(4);
        String messageId = String.valueOf(UUID.randomUUID());
        String createTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        map.put("messageId", messageId);
        map.put("createTime", createTime);
        map.put("topicQueueName", Constants.TOPIC_MAN_QUEUE_KEY);
        map.put("message", message);

        rabbitTemplate.convertAndSend(Constants.TOPIC_EXCHANGE_KEY, Constants.TOPIC_MAN_QUEUE_KEY, map);

        return "ok";
    }

    /**
     * woman队列
     * @param message
     * @return
     */
    @RequestMapping(value = "/woman/send/msg")
    public String womanSendMessage(String message) {
        Map<String, Object> map = new HashMap<>(4);
        String messageId = String.valueOf(UUID.randomUUID());
        String createTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        map.put("messageId", messageId);
        map.put("createTime", createTime);
        map.put("topicQueueName", Constants.TOPIC_WOMAN_QUEUE_KEY);
        map.put("message", message);

        rabbitTemplate.convertAndSend(Constants.TOPIC_EXCHANGE_KEY, Constants.TOPIC_WOMAN_QUEUE_KEY, map);

        return "ok";
    }
}
