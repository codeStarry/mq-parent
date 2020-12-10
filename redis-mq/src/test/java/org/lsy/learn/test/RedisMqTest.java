package org.lsy.learn.test;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.lsy.learn.redismq.RedisMqApplication;
import org.lsy.learn.redismq.mq.RedisConsumer;
import org.lsy.learn.redismq.mq.RedisProduce;
import org.lsy.learn.redismq.publish.RedisPublish;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = {RedisMqApplication.class})
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class RedisMqTest {

    private final RedisProduce produce;

    private final RedisConsumer consumer;

    private final RedisPublish publish;

    @Test
    public void redisMq() {
        produce.produce("第一条消息");

        consumer.consumer();
    }

    @Test
    public void redisPubliMq() {
        publish.sendMessage("第一个通道", "你好呀");
        publish.sendMessage("第一个通道", "发发阿凡达");
        publish.sendMessage("第二个通道", "门刀法附件");
    }
}
