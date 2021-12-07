//package com.qjc.mq.config;
//
//import com.qjc.mq.constant.RabbitMQConstant;
//import com.rabbitmq.client.Channel;
//import org.springframework.amqp.core.Message;
//import org.springframework.amqp.core.MessageProperties;
//import org.springframework.amqp.rabbit.listener.api.ChannelAwareMessageListener;
//import org.springframework.stereotype.Component;
//
//import java.io.IOException;
//import java.util.List;
//
///**
// * @ClassName: MyAckReceiver
// * @Description: 手动确认消息监听类
// * @Author: qjc
// * @Date: 2021/12/7 3:48 下午
// */
//@Component
//public class MyAckReceiver implements ChannelAwareMessageListener {
//    /**
//     * 处理多个队列
//     */
//    @Override
//    public void onMessage(Message message, Channel channel) throws IOException {
//        String msg = new String(message.getBody());
//        MessageProperties messageProperties = message.getMessageProperties();
//        long deliveryTag = messageProperties.getDeliveryTag();
//        String messageId = messageProperties.getMessageId();
//        String correlationId = messageProperties.getCorrelationId();
//        // 队列名
//        String consumerQueue = message.getMessageProperties().getConsumerQueue();
//        if (RabbitMQConstant.QUEUE_TOPIC_FIRST.equals(consumerQueue)) {
//            System.err.println("11111111111111111消费者" + consumerQueue + "接收到的消息: " + msg);
//        } else if (RabbitMQConstant.QUEUE_TOPIC_SECOND.equals(consumerQueue)) {
//            System.out.println("222222222222222消费者" + consumerQueue + "接收到的消息: " + msg);
//        } else {
//            System.out.println("MyAckReceiver deliveryTag:" + deliveryTag + "，correlationId:" + correlationId + "，messageId:" + messageId);
//        }
//        try {
//            //第二个参数手动确认可以被批量处理，当参数为true时，则可以一次性确认delivery_tag小于等于传入值的所有消息
//            channel.basicAck(deliveryTag, true);
//            //第二个参数，true会重新放回队列，所以需要自己根据业务逻辑判断什么时候使用拒绝
//            //channel.basicReject(deliveryTag, true);
//        } catch (IOException e) {
//            channel.basicReject(deliveryTag, false);
//            e.printStackTrace();
//        }
//    }
//
//    @Override
//    public void onMessage(Message message) {
//
//    }
//
//    @Override
//    public void onMessageBatch(List<Message> messages, Channel channel) {
//
//    }
//}
//
//
