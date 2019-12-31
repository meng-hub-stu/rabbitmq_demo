package com.qjc.mq.mqsender.direct;

import com.qjc.mq.constant.RabbitMQConstant;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.UUID;

/**
 * @Description:
 * @Author: qjc
 * @Date: 2019/12/6
 */
@Component
public class DirectSender {

    @Resource
    RabbitTemplate rabbitTemplate;


    public void send(Integer i) {
        String msg = "Hello Msg -->" + i;
        rabbitTemplate.convertAndSend(RabbitMQConstant.EXCHANGE_DIRECT, RabbitMQConstant.ROUTING_KEY_EASY, msg, new CorrelationData(UUID.randomUUID().toString().replaceAll("-", "")));
    }


}
