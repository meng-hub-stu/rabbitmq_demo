package com.qjc.mq.mqsender.topic;

import com.qjc.mq.constant.RabbitMQConstant;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Random;
import java.util.UUID;

/**
 * @Description:
 * @Author: qjc
 * @Date: 2021/12/6
 */
@Component
public class TopicSender {

    @Resource
    RabbitTemplate rabbitTemplate;

    private String[] address = {"bj", "sh"};
    private String[] level = {"error", "info"};
    private Random random = new Random();


    public void send(int i) {
        String routingKey = address[random.nextInt(address.length)] + "." + level[random.nextInt(level.length)] + ".log";
        String message = routingKey + "-----" + i;
        rabbitTemplate.convertAndSend(RabbitMQConstant.EXCHANGE_TOPIC, routingKey, message, new CorrelationData(UUID.randomUUID().toString().replaceAll("-", "")));
    }


}
