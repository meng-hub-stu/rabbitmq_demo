//package com.qjc.mq.config;
//
//import com.qjc.mq.constant.RabbitMQConstant;
//import org.springframework.amqp.core.AcknowledgeMode;
//import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
//import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import javax.annotation.Resource;
//
///**
// * @ClassName: MessageListenerConfig
// * @Description: 自定义消息监听容器
// * @Author: qjc
// * @Date: 2021/12/7 3:47 下午
// */
//@Configuration
//public class MessageListenerConfig {
//    @Resource
//    private CachingConnectionFactory cachingConnectionFactory;
//    @Resource
//    private MyAckReceiver myAckReceiver;
//
//    /**
//     * 自定义监听容器，不使用@RabbitListen 注解来消费消息，而是使用自定义的SimpleMessageListenerContainer来消费消息
//     * PS：如果只用自定义监听器，则可以将配置文件中的spring.rabbitmq.listener注释掉
//     *
//     * @author qjc
//     * @date 2021/12/7 3:50 下午
//     */
//    @Bean
//    public SimpleMessageListenerContainer simpleMessageListenerContainer() {
//        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer(cachingConnectionFactory);
//        //设置最小并发的消费者数量
//        container.setConcurrentConsumers(1);
//        //设置最大并发的消费者数量
//        container.setMaxConcurrentConsumers(20);
//        //限流，单位时间内消费多少条记录
//        container.setPrefetchCount(10);
//        //设置rabbit 确认消息的模式，默认是自动确认
//        container.setAcknowledgeMode(AcknowledgeMode.MANUAL);
//        // 监听队列名称（多个）在此只针对topic模式的两个队列
//        container.setQueueNames(RabbitMQConstant.QUEUE_TOPIC_FIRST, RabbitMQConstant.QUEUE_TOPIC_SECOND);
//        //设置消息监听类
//        container.setMessageListener(myAckReceiver);
//        return container;
//    }
//}
//
//
