package org.lsy.learn.redismq.publish;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

/**
 * 发布消息
 */
@Component
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class RedisPublish {


    private final StringRedisTemplate redisTemplate;


    public void sendMessage(String channel, Object obj) {
        redisTemplate.convertAndSend(channel, obj);
    }
}
