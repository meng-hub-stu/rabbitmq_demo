package com.qjc.mq.mqsender.ttl;

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
 * @ClassName: TTLSender
 * @Description:
 * @Author: qjc
 * @Date: 2021/12/8 10:24 上午
 */
@Component
public class TTLSender {

    @Resource
    RabbitTemplate rabbitTemplate;


    public void send(Integer i) {
        String msg = "Hello Msg -->" + i;
        Message message = MessageBuilder.withBody(msg.getBytes()).build();
        // 消息持久化
        message.getMessageProperties().setDeliveryMode(MessageDeliveryMode.PERSISTENT);
        message.getMessageProperties().setContentType(MessageProperties.CONTENT_TYPE_JSON);
        // 消息设置过期时间10s（超过10s没有被消费，则消息变成死信）
        message.getMessageProperties().setExpiration("10000");
        rabbitTemplate.convertAndSend(RabbitMQConstant.EXCHANGE_TTL, RabbitMQConstant.ROUTING_KEY_TTL, message, new CorrelationData(UUID.randomUUID().toString().replaceAll("-", "")));
    }
}
