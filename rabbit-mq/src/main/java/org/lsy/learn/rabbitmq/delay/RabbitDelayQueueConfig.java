package org.lsy.learn.rabbitmq.delay;

import org.lsy.learn.rabbitmq.config.Constants;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.CustomExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * rabbit延迟队列配置
 */
@Configuration
public class RabbitDelayQueueConfig {

    /**
     * 创建交换机
     * @return
     */
    @Bean
    public CustomExchange customExchange() {
        Map<String, Object> map = new HashMap<>(1);
        map.put("x-delayed-type", "direct");
        return new CustomExchange(Constants.DELAY_EXCHANGE_KEY, "x-delayed-message", true, false, map);
    }

    @Bean
    public Queue queue() {
        return new Queue(Constants.DELAY_QUEUE_KEY);
    }

    @Bean
    public Binding binding() {
        return BindingBuilder.bind(queue()).to(customExchange()).with(Constants.DELAY_ROUTING_KEY).noargs();
    }
}
