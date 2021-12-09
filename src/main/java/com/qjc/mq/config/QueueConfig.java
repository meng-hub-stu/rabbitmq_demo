package com.qjc.mq.config;

import com.qjc.mq.constant.RabbitMQConstant;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description:
 * @Author: qjc
 * @Date: 2021/12/6
 */
@Configuration
public class QueueConfig {

    /**
     * durable="true" 持久化 rabbitmq重启的时候不需要创建新的队列
     * exclusive 表示该消息队列是否只在当前connection生效,默认是false
     * auto-delete 表示消息队列没有在使用时将被自动删除 默认是false
     */

    @Bean(name = RabbitMQConstant.QUEUE_DIRECT)
    public Queue directQueue() {
        return new Queue(RabbitMQConstant.QUEUE_DIRECT, true, false, false);
    }

    @Bean(name = RabbitMQConstant.QUEUE_TTL)
    public Queue ttlQueue() {
        Map<String, Object> arguments = new HashMap<>(2);
        // 设置队列中所有消息过期时间30s
        arguments.put("x-message-ttl", 30000);
        // 每次修改属性需要先提前删除队列，否在会报错
        // 设置队列的空闲存活时间20s（如果该队列在20s内没有消费者则该队列就会被销毁,PS:durable=false）
//        arguments.put("x-expires", 20000);
        // 设置队列可以存储的最大消息数量
//        arguments.put("x-max-length", 1000);
        return new Queue(RabbitMQConstant.QUEUE_TTL, false, false, false, arguments);
    }

    @Bean(name = RabbitMQConstant.QUEUE_DLX_NORMAL)
    public Queue dlxNormalQueue() {
        Map<String, Object> arguments = new HashMap<>(4);
        // 设置队列中所有消息过期时间30s
        arguments.put("x-message-ttl", 30000);
        // 设置该队列所关联的死信交换器（当队列消息TTL到期后依然没有被消费，则加入死信队列）
        arguments.put("x-dead-letter-exchange", RabbitMQConstant.EXCHANGE_DLX);
        // 设置该队列所关联的死信交换器的routingkey，如果没有特殊指定，使用原来队列的routingkey
        arguments.put("x-dead-letter-routing-key", RabbitMQConstant.ROUTING_KEY_DLX);

        return new Queue(RabbitMQConstant.QUEUE_DLX_NORMAL, true, false, false, arguments);
    }

    @Bean(name = RabbitMQConstant.QUEUE_DLX)
    public Queue dlxQueue() {
        return new Queue(RabbitMQConstant.QUEUE_DLX, true, false, false);
    }

    @Bean(name = RabbitMQConstant.QUEUE_WORK)
    public Queue workQueue() {
        return new Queue(RabbitMQConstant.QUEUE_WORK, true, false, false);
    }

    @Bean(name = RabbitMQConstant.QUEUE_TOPIC_FIRST)
    public Queue topicQueueFirst() {
        return new Queue(RabbitMQConstant.QUEUE_TOPIC_FIRST, true, false, false);
    }

    @Bean(name = RabbitMQConstant.QUEUE_TOPIC_SECOND)
    public Queue topicQueueSecond() {
        return new Queue(RabbitMQConstant.QUEUE_TOPIC_SECOND, true, false, false);
    }

    @Bean(name = RabbitMQConstant.QUEUE_FANOUT_FIRST)
    public Queue fanoutQueueFirst() {
        return new Queue(RabbitMQConstant.QUEUE_FANOUT_FIRST, true, false, false);
    }

    @Bean(name = RabbitMQConstant.QUEUE_FANOUT_SECOND)
    public Queue fanoutQueueSecond() {
        return new Queue(RabbitMQConstant.QUEUE_FANOUT_SECOND, true, false, false);
    }

    /**
     * PS：安装rabbitmq_delayed_message_exchange插件，使用延迟队列
     */
    @Bean(name = RabbitMQConstant.QUEUE_DELAY)
    public Queue delayQueue() {
        return new Queue(RabbitMQConstant.QUEUE_DELAY, true, false, false);
    }

}
