package com.qjc.mq.mqreceiver.dlx;

import cn.hutool.core.date.DateUtil;
import com.qjc.mq.constant.RabbitMQConstant;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @ClassName: DLXReceiver
 * @Description: 死信队列消费者
 * @Author: qjc
 * @Date: 2021/12/9 5:03 下午
 */
@Component
@Slf4j
public class DLXReceiver {

    @RabbitListener(queues = RabbitMQConstant.QUEUE_DLX)
    public void process(Message message, Channel channel) throws Exception {
        String messageContent = new String(message.getBody());
        System.err.println("消费者消费死信队列消息: 【" + messageContent + "】，消费时间：【" + DateUtil.format(new Date(), "yyyy-MM-dd HH:mm:ss") + "】");
        long deliveryTag = message.getMessageProperties().getDeliveryTag();
        channel.basicAck(deliveryTag, false);
    }
}
