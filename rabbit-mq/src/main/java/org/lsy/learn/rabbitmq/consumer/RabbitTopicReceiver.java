package org.lsy.learn.rabbitmq.consumer;

import org.lsy.learn.rabbitmq.config.Constants;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 主题交换机监听类
 */
@Component
@RabbitListener(queues = {Constants.TOPIC_MAN_QUEUE_KEY, Constants.TOPIC_WOMAN_QUEUE_KEY})
public class RabbitTopicReceiver {

    @RabbitHandler
    public void receiver(Map map) {
        String queueName = map.get("topicQueueName").toString();
        System.out.println("主题交换机，队列：" + queueName + "，收到消息：" + map);
    }
}
