package com.example.consumer.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author FPH
 * @since 2022年8月19日16:11:34
 * TestDirectQueue -》监听的队列名称
 */
@Component
@RabbitListener(queues = "TestDirectQueue")
@Slf4j
public class DirectReceiver {

    @RabbitHandler
    public void process(Map testMsg){
        log.info("一：消费者接收的信息："+testMsg);
    }
}
