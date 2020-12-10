package org.lsy.learn.rabbitmq.consumer;

import org.lsy.learn.rabbitmq.config.Constants;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 扇形交换机监听类
 */
@Component
@RabbitListener(queues = {Constants.FANOUT_ONE_QUEUE_KEY, Constants.FANOUT_TWO_QUEUE_KEY, Constants.FANOUT_THREE_QUEUE_KEY})
public class RabbitFanoutReceiver {

    @RabbitHandler
    public void receiver(Map map) {
        System.out.println("收到消息：" + map.toString());
    }
}
