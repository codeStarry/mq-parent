package org.lsy.learn.rabbitmq.consumer;

import org.lsy.learn.rabbitmq.config.Constants;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 直连交换机监听类
 *
 * @RabbitListener：设置监听的队列名称
 */
@Component
@RabbitListener(queues = {Constants.DIRECT_QUEUE_KEY})
public class RabbitDirectReceiver {

    @RabbitHandler
    public void receiver(Map msgMap) {
        System.out.println("directExchange消费者收到消息：" + msgMap.toString());
    }
}
