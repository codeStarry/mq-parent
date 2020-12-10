package org.lsy.learn.rabbitmq.provider;

import org.lsy.learn.rabbitmq.config.Constants;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 扇形交换机配置
 */
@Configuration
public class RabbitFanoutExchange {

    @Bean
    public FanoutExchange fanoutExchange() {
        return new FanoutExchange(Constants.FANOUT_EXCHANGE_KEY);
    }

    @Bean
    public Queue onQueue() {
        return new Queue(Constants.FANOUT_ONE_QUEUE_KEY);
    }

    @Bean
    public Queue twoQueue() {
        return new Queue(Constants.FANOUT_TWO_QUEUE_KEY);
    }

    @Bean
    public Queue threeQueue() {
        return new Queue(Constants.FANOUT_THREE_QUEUE_KEY);
    }

    @Bean
    public Binding bindingOneExchange() {
        return BindingBuilder.bind(onQueue()).to(fanoutExchange());
    }

    @Bean
    public Binding bindingTwoExchange() {
        return BindingBuilder.bind(twoQueue()).to(fanoutExchange());
    }

    @Bean
    public Binding bindingThreeExchange() {
        return BindingBuilder.bind(threeQueue()).to(fanoutExchange());
    }
}
