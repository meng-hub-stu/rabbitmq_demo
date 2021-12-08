//package com.qjc.mq.mqreceiver.ttl;
//
//import com.qjc.mq.constant.RabbitMQConstant;
//import com.rabbitmq.client.Channel;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.amqp.core.Message;
//import org.springframework.amqp.rabbit.annotation.RabbitListener;
//import org.springframework.stereotype.Component;
//
///**
// * @ClassName: TTLReceiver
// * @Description: 设置了TTL的消息/队列没有消费者存在的情况下都会消失，所以测试的时候将消费者注释掉
// * @Author: qjc
// * @Date: 2021/12/8 10:30 上午
// */
//@Component
//@Slf4j
//public class TTLReceiver {
//
//    @RabbitListener(queues = RabbitMQConstant.QUEUE_TTL)
//    public void process(Message message, Channel channel) throws Exception {
//        String messageContent = new String(message.getBody());
//        log.info("消费者接收消息: " + messageContent);
//        long deliveryTag = message.getMessageProperties().getDeliveryTag();
//        log.info("索引为{}的消息被消费", deliveryTag);
//        try {
//            channel.basicAck(deliveryTag, false);
//        } catch (Exception e) {
//            log.error("消费消息：" + messageContent + "——>出现异常", e);
//            channel.basicNack(deliveryTag, false, true);
//            // 手动拒绝消息。第二个参数表示是否重新入列
//            // channel.basicReject(deliveryTag, true);
//            throw new Exception("消费异常");
//        }
//    }
//
//
//}
