package com.example.producer.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.UUID;

/**
 * @author FPH
 * @since 2022年8月19日14:33:42
 * 直连生产者控制器
 */
@RestController
@RequestMapping("producer")
@RequiredArgsConstructor
@Api(tags = "生产者")
public class DirectController {

    private final RabbitTemplate rabbitTemplate;

    @ApiOperation("发送一条当前时间")
    @GetMapping("direct")
    public String directMsg(){
        String messageId = UUID.randomUUID().toString().replace("-", "");
        String messageBody = "hello world";
        String createDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        HashMap<String, Object> map = new HashMap<>();
        map.put("messageId", messageId);
        map.put("messageBody", messageBody);
        map.put("createDate", createDate);
        //将消息携带绑定键值：TestDirectRouting 发送到交换机TestDirectExchange
        rabbitTemplate.convertAndSend("TestDirectExchange", "TestDirectRouting", map);
        return "发送的信息为："+map;
    }
}
