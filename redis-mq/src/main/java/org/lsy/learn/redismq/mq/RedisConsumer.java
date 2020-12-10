package org.lsy.learn.redismq.mq;

import cn.hutool.core.thread.NamedThreadFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.lsy.learn.redismq.config.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@Slf4j
@Service
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class RedisConsumer {

    private final StringRedisTemplate redisTemplate;

    public void consumer() {
        ExecutorService service = Executors.newSingleThreadExecutor(new NamedThreadFactory("redis-mq-", false));
        service.execute(() -> {
            while (true) {
                String result = redisTemplate.opsForList().rightPop(Constants.REDIS_MQ_KEY, 1, TimeUnit.SECONDS);
                if (Objects.isNull(result)) {
                    continue;
                }

                log.info("消费者收到消息，开始消费：{}", result);
            }
        });
    }
}
