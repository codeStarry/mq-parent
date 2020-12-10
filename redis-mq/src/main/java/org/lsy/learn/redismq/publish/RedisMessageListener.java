package org.lsy.learn.redismq.publish;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;

/**
 * 消息配置
 */
@Configuration
public class RedisMessageListener {



    @Bean
    public RedisMessageListenerContainer container(RedisConnectionFactory factory,
                                                           MessageListenerAdapter adapter1,
                                                           MessageListenerAdapter adapter2) {

        RedisMessageListenerContainer container = new RedisMessageListenerContainer();

        container.setConnectionFactory(factory);
        container.addMessageListener(adapter1, new PatternTopic("第一个通道"));
        container.addMessageListener(adapter2, new PatternTopic("第二个通道"));

        return container;
    }

    @Bean
    public MessageListenerAdapter adapter1(RedisReceiver receiver) {
        //MessageListenerAdapter adapter = new MessageListenerAdapter(receiver, "receiver1");
        return new MessageListenerAdapter(receiver, "receiver1");
    }

    @Bean
    public MessageListenerAdapter adapter2(RedisReceiver receiver) {
        return new MessageListenerAdapter(receiver, "receiver2");
    }
}
