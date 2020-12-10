package org.lsy.learn.test;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.lsy.learn.rabbitmq.RabbitMqApplication;
import org.lsy.learn.rabbitmq.config.Constants;
import org.lsy.learn.rabbitmq.consumer.RabbitDirectReceiver;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@SpringBootTest(classes = {RabbitMqApplication.class})
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class RabbitMqTest {

    private final RabbitTemplate rabbitTemplate;
    private final RabbitDirectReceiver directReceiver;

    /**
     * 测试直连交换机
     */
    @Test
    public void directExchange() {
        Map<String, Object> map = new HashMap<>(3);
        String messageId = String.valueOf(UUID.randomUUID());
        String createTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        map.put("messageId", messageId);
        map.put("createTime", createTime);
        map.put("message", "");

        rabbitTemplate.convertAndSend(Constants.DIRECT_EXCHANGE_KEY, Constants.DIRECT_ROUTING_KEY, map);
    }
}
