package org.lsy.learn.redismq;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@SpringBootApplication
public class RedisMqApplication {
    public static void main(String[] args) {
        SpringApplication.run(RedisMqApplication.class, args);
    }
}
