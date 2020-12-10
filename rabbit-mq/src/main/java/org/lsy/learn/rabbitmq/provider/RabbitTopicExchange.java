package org.lsy.learn.rabbitmq.provider;

import org.lsy.learn.rabbitmq.config.Constants;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 主题交换机配置
 */
@Configuration
public class RabbitTopicExchange {

    /**
     * 创建主题交换机
     * @return
     */
    @Bean
    public TopicExchange topicExchange() {
        return new TopicExchange(Constants.TOPIC_EXCHANGE_KEY);
    }

    /**
     * 创建 man 队列
     * @return
     */
    @Bean
    public Queue manQueue() {
        return new Queue(Constants.TOPIC_MAN_QUEUE_KEY);
    }

    /**
     * 创建 woman 队列
     * @return
     */
    @Bean
    public Queue womanQueue() {
        return new Queue(Constants.TOPIC_WOMAN_QUEUE_KEY);
    }

    @Bean
    public Binding bindingManExchange() {
        return BindingBuilder.bind(manQueue()).to(topicExchange()).with(Constants.TOPIC_MAN_QUEUE_KEY);
    }


    @Bean
    public Binding bindingWomanExchange() {
        return BindingBuilder.bind(womanQueue()).to(topicExchange()).with(Constants.TOPIC_ROUTING_KEY);
    }
}
