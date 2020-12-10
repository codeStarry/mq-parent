package org.lsy.learn.rabbitmq.delay;

import org.lsy.learn.rabbitmq.config.Constants;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
@RabbitListener(queues = {Constants.DELAY_QUEUE_KEY})
public class RabbitDelayConsumer {

    @RabbitHandler
    public void receive(Object obj) {
        System.out.println("延迟队列，收到消息：" + obj.toString());
        System.out.println(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
    }
}
