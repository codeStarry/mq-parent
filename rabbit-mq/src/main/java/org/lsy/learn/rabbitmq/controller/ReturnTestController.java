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
 * 回调函数测试
 */
@RestController
@RequestMapping(value = "/return")
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class ReturnTestController {

    private final RabbitTemplate rabbitTemplate;

    /**
     * 消息推送到server，但是在server里找不到交换机
     *
     * 回调了 @{setConfirmCallback()}
     *
     * @param message
     * @return
     */
    @RequestMapping(value = "/test/one")
    public String test1(String message) {
        Map<String, Object> map = new HashMap<>(3);
        String messageId = String.valueOf(UUID.randomUUID());
        String createTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        map.put("messageId", messageId);
        map.put("createTime", createTime);
        map.put("message", message);

        rabbitTemplate.convertAndSend("non-exchange", Constants.DIRECT_ROUTING_KEY, map);

        return "ok";
    }

    /**
     * 消息推送到server，找到交换机了，但是没找到队列
     *
     * 回调了 @{setConfirmCallback()} 函数和 @{setReturnCallback()} 函数
     *
     * @param message
     * @return
     */
    @RequestMapping(value = "/test/two")
    public String test2(String message) {
        Map<String, Object> map = new HashMap<>(3);
        String messageId = String.valueOf(UUID.randomUUID());
        String createTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        map.put("messageId", messageId);
        map.put("createTime", createTime);
        map.put("message", message);

        rabbitTemplate.convertAndSend(Constants.DIRECT_TEST_EXCHANGE, Constants.DIRECT_ROUTING_KEY, map);

        return "ok";
    }

    /**
     * 消息推送到sever，交换机和队列啥都没找到
     *
     * 回调了 @{setConfirmCallback()}
     *
     * @param message
     * @return
     */
    @RequestMapping(value = "/test/three")
    public String test3(String message) {
        Map<String, Object> map = new HashMap<>(3);
        String messageId = String.valueOf(UUID.randomUUID());
        String createTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        map.put("messageId", messageId);
        map.put("createTime", createTime);
        map.put("message", message);

        rabbitTemplate.convertAndSend("fda", "dfs", map);

        return "ok";
    }
}
