package com.qjc.mq.mqreceiver.topic;

import com.qjc.mq.constant.RabbitMQConstant;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @Description:
 * @Author: qjc
 * @Date: 2019/12/6
 */
@Component
public class TopicReceiverSecond {

    @RabbitListener(queues = RabbitMQConstant.QUEUE_TOPIC_SECOND)
    @RabbitHandler
    public void process(String msg) {
        System.out.println("222222222222222消费者topic.queue.second接收到的消息: " + msg);
    }


}
