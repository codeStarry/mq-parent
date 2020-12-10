package org.lsy.learn.rabbitmq.consumer;

import org.lsy.learn.rabbitmq.config.Constants;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 第二个直连交换机监听类
 */
@Component
@RabbitListener(queues = {Constants.DIRECT_QUEUE_KEY})
public class RabbitDirectReceiverNew {

    @RabbitHandler
    public void receiver(Map msgMap) {
        System.out.println("第二个直连交换机收到的消息：" + msgMap.toString());
    }
}
