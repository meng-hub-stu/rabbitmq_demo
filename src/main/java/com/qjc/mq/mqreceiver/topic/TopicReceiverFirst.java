package com.qjc.mq.mqreceiver.topic;

import com.qjc.mq.constant.RabbitMQConstant;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @Description:
 * @Author: qjc
 * @Date: 2021/12/6
 */
@Component
@Slf4j
public class TopicReceiverFirst {

    @RabbitListener(queues = RabbitMQConstant.QUEUE_TOPIC_FIRST)
    @RabbitHandler
    public void process(Message message, Channel channel) throws Exception {
        String messageContent = new String(message.getBody());
        System.err.println("11111111111111111消费者topic.queue.first接收到的消息: " + messageContent);
        try {
            long deliveryTag = message.getMessageProperties().getDeliveryTag();
//            int a = 1 / 0;
            /**
             * void basicAck(long deliveryTag, boolean multiple) throws IOException;
             * deliveryTag：该消息的index
             * multiple：是否批量处理.true:将一次性ack所有小于deliveryTag的消息
             */
            channel.basicAck(deliveryTag, false);
        } catch (Exception e) {
            log.error("消费消息：" + messageContent + "——>出现异常", e);
            throw new Exception("消费异常");
        }
    }


}
