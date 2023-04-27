package com.qjc.mq.mqreceiver.delay;

import com.qjc.mq.constant.RabbitMQConstant;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @ClassName: DelayReceiver
 * @Description:
 * @Author: qjc
 * @Date: 2021/12/9 11:39 上午
 */
@Component
@Slf4j
public class DelayReceiver {

    /**
     * PS：安装rabbitmq_delayed_message_exchange插件，使用延迟队列
     */
    @RabbitListener(queues = RabbitMQConstant.QUEUE_DELAY)
    public void process(Message message, Channel channel) throws Exception {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.err.println("接到通知【" + new String(message.getBody(), "utf-8") + "】，接收时间【" + simpleDateFormat.format(new Date()) + "】");
        long deliveryTag = message.getMessageProperties().getDeliveryTag();
        channel.basicAck(deliveryTag, false);
    }

}
