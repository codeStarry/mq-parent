package org.lsy.learn.rabbitmq.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * rabbitmq配置，开启回调函数
 */
@Slf4j
@Configuration
public class RabbitConfig {

    @Bean
    public RabbitTemplate createRabbitTemplate(ConnectionFactory factory) {
        RabbitTemplate template = new RabbitTemplate();
        template.setConnectionFactory(factory);
        template.setMandatory(true);

        template.setConfirmCallback(new RabbitTemplate.ConfirmCallback() {

            @Override
            public void confirm(CorrelationData correlationData, boolean b, String s) {
                log.info("ConfirmCallback:  相关数据：{}", correlationData);
                log.info("ConfirmCallback:  确认情况：{}", b);
                log.info("ConfirmCallback:  原因：{}", s);
            }
        });

        template.setReturnCallback(new RabbitTemplate.ReturnCallback() {

            @Override
            public void returnedMessage(Message message, int i, String s, String s1, String s2) {
                log.info("ReturnCallback:     消息：{}", message);
                log.info("ReturnCallback:     回应码：{}", i);
                log.info("ReturnCallback:     回应信息：{}", s);
                log.info("ReturnCallback:     交换机:{}", s1);
                log.info("ReturnCallback:     路由键：{}", s2);
            }
        });

        return template;
    }
}
