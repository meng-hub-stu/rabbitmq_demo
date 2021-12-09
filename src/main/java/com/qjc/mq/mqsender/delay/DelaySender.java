package com.qjc.mq.mqsender.delay;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import com.qjc.mq.constant.RabbitMQConstant;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;
import java.util.UUID;

/**
 * @ClassName: DelaySender
 * @Description:
 * @Author: qjc
 * @Date: 2021/12/9 11:42 上午
 */
@Component
public class DelaySender {

    @Resource
    RabbitTemplate rabbitTemplate;

    /**
     * PS：安装rabbitmq_delayed_message_exchange插件，使用延迟队列
     */
    public String send(Integer seconds) {
        DateTime dateTime = DateUtil.offsetSecond(new Date(), seconds);
        String msg = "通知时间（" + DateUtil.format(new Date(), "yyyy-MM-dd HH:mm:ss") + "），通知内容（" + DateUtil.formatDateTime(dateTime) + "召开会议）";
        Message message = MessageBuilder.withBody(msg.getBytes()).build();
        // RabbitMQ只会检查队列头部的消息是否过期，如果过期就放到死信队列
        // 假如第一个过期时间很长，10s，第二个消息3s，则系统先看第一个消息，等 到第一个消息过期，放到DLX
        // 此时才会检查第二个消息，但实际上此时第二个消息早已经过期了，但是并没 有先于第一个消息放到DLX。
        // 插件rabbitmq_delayed_message_exchange帮我们搞定这个。
        MessageProperties messageProperties = message.getMessageProperties();
        // 设置到期时间，也就是提前10s提醒
        messageProperties.setDelay((seconds - 10) * 1000);
        rabbitTemplate.convertAndSend(RabbitMQConstant.EXCHANGE_DELAY, RabbitMQConstant.ROUTING_KEY_DELAY, message, new CorrelationData(UUID.randomUUID().toString().replaceAll("-", "")));
        return seconds + "秒后召开会议，已经定好闹钟了，到时提前告诉大家";
    }

}
