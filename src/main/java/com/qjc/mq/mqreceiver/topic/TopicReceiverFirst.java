package com.qjc.mq.mqreceiver.topic;

import com.qjc.mq.constant.RabbitMQConstant;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @Description:
 * @Author: qjc
 * @Date: 2021/12/6
 */
@Component
public class TopicReceiverFirst {

    @RabbitListener(queues = RabbitMQConstant.QUEUE_TOPIC_FIRST)
    @RabbitHandler
    public void process(String msg) {
        System.err.println("11111111111111111消费者topic.queue.first接收到的消息: " + msg);
    }


}
