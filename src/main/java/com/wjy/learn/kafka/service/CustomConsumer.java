package com.wjy.learn.kafka.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

/**
 * 自定义消费者</br>
 * 配置@KafkaListener(containerFactory = xxx)参考org.springframework.kafka.config.KafkaListenerContainerFactory</br>
 * 配置spring.kafka.listener.xxx</br>
 * 参考org.springframework.kafka.listener.AckMode
 */
@Slf4j
@Component
public class CustomConsumer {

    /**
     * 简单的延迟队列实现：满足条件，消息转移到另一个主题</br>
     */
    @KafkaListener(topics = { "${wjy.learn.kafka.topic}" })
    @SendTo(value = { "${wjy.learn.kafka.delay-topic}" })
    public String consumeMsg(String msg) {
        if ("delay".equals(msg)) {
            // 消息转移到延迟主题
            return msg;
        } else {
            log.info("consume msg: {}", msg);
            return null;
        }
    }

    /**
     * 延迟消费消息
     */
    @SuppressWarnings("static-access")
    @KafkaListener(topics = { "${wjy.learn.kafka.delay-topic}" })
    public String delayConsumeMsg(String msg) {
        try {
            // 延迟
            Thread.currentThread().sleep(5000);
        } catch (InterruptedException e) {
            log.error(e.getMessage(), e);
        }
        log.info("consume delay msg: {}", msg);
        return msg;
    }

}
