package org.lsy.learn.rabbitmq.provider;

import org.lsy.learn.rabbitmq.config.Constants;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 直连交换机配置
 */
@Configuration
public class RabbitDirectExchange {

    /**
     * 创建队列，取名为：testDirectQueue  持久化
     * @return
     */
    @Bean
    public Queue testDirectQueue() {
        /**
         * durable:是否持久化，默认false；持久化队列：消息会被存储在磁盘上，当消息代理服务重启后任然存在，
         *         暂存队列：当前连接有效
         * exclusive:默认false，只能被当前创建的连接使用，当连接关闭后队列即被删除，此优先级高于durable
         * autoDelete:是否自动删除，当没有生产者和消费者使用该队列时会被自动删除
         */
        return new Queue(Constants.DIRECT_QUEUE_KEY, true);
    }

    /**
     * 创建直连交换机，取名为：testDirectExchange  持久化
     * @return
     */
    @Bean
    public DirectExchange testDirectExchange() {
        /**
         * durable:是否持久化，默认false；持久化队列：消息会被存储在磁盘上，当消息代理服务重启后任然存在，
         *         暂存队列：当前连接有效
         * exclusive:默认false，只能被当前创建的连接使用，当连接关闭后队列即被删除，此优先级高于durable
         * autoDelete:是否自动删除，当没有生产者和消费者使用该队列时会被自动删除
         */
        return new DirectExchange(Constants.DIRECT_EXCHANGE_KEY, true, false);
    }

    /**
     * 将队列和交换机绑定，并设置匹配键：testDirectRouting
     * @return
     */
    @Bean
    public Binding bindingDirect() {
        return BindingBuilder.bind(testDirectQueue()).to(testDirectExchange()).with(Constants.DIRECT_ROUTING_KEY);
    }

    /**
     * 测试回调函数用
     * @return
     */
    @Bean
    public DirectExchange exchange() {
        return new DirectExchange(Constants.DIRECT_TEST_EXCHANGE);
    }
}
