package org.lsy.learn.redismq.publish;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * 消息订阅者
 */
@Slf4j
@Component
public class RedisReceiver {

    public void receiver1(String message) {
        log.info("第一个通道发来消息：{}", message);
    }

    public void receiver2(String message) {
        log.info("第二个通道发来消息：{}", message);
    }
}
