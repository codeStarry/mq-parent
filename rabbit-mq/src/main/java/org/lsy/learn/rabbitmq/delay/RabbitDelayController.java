package org.lsy.learn.rabbitmq.delay;

import lombok.RequiredArgsConstructor;
import org.lsy.learn.rabbitmq.config.Constants;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;
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
@RequestMapping(value = "/delay")
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class RabbitDelayController {

    private final RabbitTemplate rabbitTemplate;

    @RequestMapping(value = "/send/msg")
    public String delayTest(String message) {
        Map<String, Object> map = new HashMap<>();
        String messageId = String.valueOf(UUID.randomUUID());
        String createTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        map.put("messageId", messageId);
        map.put("createTime", createTime);
        map.put("message", message);

        rabbitTemplate.convertAndSend(Constants.DELAY_EXCHANGE_KEY, Constants.DELAY_ROUTING_KEY, map, new MessagePostProcessor() {
            @Override
            public Message postProcessMessage(Message message) throws AmqpException {
                message.getMessageProperties().setHeader("x-delay", 5000);
                return message;
            }
        });

        return "ok";
    }
}
