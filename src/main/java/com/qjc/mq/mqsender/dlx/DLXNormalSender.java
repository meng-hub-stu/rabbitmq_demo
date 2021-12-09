package com.qjc.mq.mqsender.dlx;

import cn.hutool.core.date.DateUtil;
import com.qjc.mq.constant.RabbitMQConstant;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.core.MessageDeliveryMode;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;
import java.util.UUID;

/**
 * @ClassName: DLXSender
 * @Description:
 * @Author: qjc
 * @Date: 2021/12/8 11:08 上午
 */
@Component
public class DLXNormalSender {
    @Resource
    RabbitTemplate rabbitTemplate;


    public String send(Integer seconds) {
        String msg = "下单时间：" + DateUtil.format(new Date(), "yyyy-MM-dd HH:mm:ss") + "，支付有效期" + seconds + "秒的订单支付到期，查看支付状态";
        Message message = MessageBuilder.withBody(msg.getBytes()).build();
        // 消息持久化
        message.getMessageProperties().setDeliveryMode(MessageDeliveryMode.PERSISTENT);
        message.getMessageProperties().setContentType(MessageProperties.CONTENT_TYPE_JSON);
        // 消息设置过期时间seconds秒（超过seconds秒没有被消费，则消息变成死信，会进入死信队列）
        message.getMessageProperties().setExpiration(String.valueOf(seconds * 1000));
        rabbitTemplate.convertAndSend(RabbitMQConstant.EXCHANGE_DLX_NORMAL, RabbitMQConstant.ROUTING_KEY_DLX_NORMAL, message, new CorrelationData(UUID.randomUUID().toString().replaceAll("-", "")));
        return "下单成功，请在" + seconds + "秒内完成支付";
    }
}
