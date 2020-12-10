package org.lsy.learn.redismq.mq;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.lsy.learn.redismq.config.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class RedisProduceImpl implements RedisProduce{

    private final StringRedisTemplate redisTemplate;

    @Override
    public void produce(String message) {
        log.info("生产者生产消息：{}", message);
        redisTemplate.opsForList().leftPush(Constants.REDIS_MQ_KEY, message);
    }

    public static void main(String[] args) {
        int x = 120;
        int pop = 0;
        while (x != 0) {

            pop = pop * 10 + x % 10;
            x = x / 10;
            System.out.println(pop);

        }
    }


}
