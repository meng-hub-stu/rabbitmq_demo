package com.qjc.mq.mqsender.direct;

import com.qjc.mq.constant.RabbitMQConstant;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.core.MessageDeliveryMode;
import org.springframework.amqp.core.MessageProperties;
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
        Message message = MessageBuilder.withBody(msg.getBytes()).build();
        message.getMessageProperties().setDeliveryMode(MessageDeliveryMode.PERSISTENT);// 消息持久化
        message.getMessageProperties().setContentType(MessageProperties.CONTENT_TYPE_JSON);
        rabbitTemplate.convertAndSend(RabbitMQConstant.EXCHANGE_DIRECT, RabbitMQConstant.ROUTING_KEY_EASY, message, new CorrelationData(UUID.randomUUID().toString().replaceAll("-", "")));
    }


}
