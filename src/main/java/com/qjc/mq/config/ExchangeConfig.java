package com.qjc.mq.config;

import com.qjc.mq.constant.RabbitMQConstant;
import org.springframework.amqp.core.*;
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
public class ExchangeConfig {

    /**
     * 交换机说明:
     * durable="true" rabbitmq重启的时候不需要创建新的交换机
     * auto-delete 表示交换机没有在使用时将被自动删除 默认是false
     * direct交换器相对来说比较简单，匹配规则为：如果路由键匹配，消息就被投送到相关的队列
     * topic交换器你采用模糊匹配路由键的原则进行转发消息到队列中
     * fanout交换器中没有路由键的概念，他会把消息发送到所有绑定在此交换器上面的队列中。
     */

    @Bean(name = RabbitMQConstant.EXCHANGE_DIRECT)
    public DirectExchange directExchange() {
        return new DirectExchange(RabbitMQConstant.EXCHANGE_DIRECT, true, false);
    }

    @Bean(name = RabbitMQConstant.EXCHANGE_TTL)
    public DirectExchange ttlExchange() {
        return new DirectExchange(RabbitMQConstant.EXCHANGE_TTL, true, false);
    }

    @Bean(name = RabbitMQConstant.EXCHANGE_DLX_NORMAL)
    public DirectExchange dlxNormalExchange() {
        return new DirectExchange(RabbitMQConstant.EXCHANGE_DLX_NORMAL, true, false);
    }

    @Bean(name = RabbitMQConstant.EXCHANGE_DLX)
    public DirectExchange dlxExchange() {
        return new DirectExchange(RabbitMQConstant.EXCHANGE_DLX, true, false);
    }

    @Bean(name = RabbitMQConstant.EXCHANGE_WORK)
    public DirectExchange workExchange() {
        return new DirectExchange(RabbitMQConstant.EXCHANGE_WORK, true, false);
    }

    @Bean(name = RabbitMQConstant.EXCHANGE_TOPIC)
    public TopicExchange topicExchange() {
        return new TopicExchange(RabbitMQConstant.EXCHANGE_TOPIC, true, false);
    }

    @Bean(name = RabbitMQConstant.EXCHANGE_FANOUT)
    public FanoutExchange fanoutExchange() {
        return new FanoutExchange(RabbitMQConstant.EXCHANGE_FANOUT, true, false);
    }

    /**
     * PS：安装rabbitmq_delayed_message_exchange插件，使用延迟队列
     */
    @Bean(name = RabbitMQConstant.EXCHANGE_DELAY)
    public DirectExchange delayExchange() {
        DirectExchange directExchange = new DirectExchange(RabbitMQConstant.EXCHANGE_DELAY, true, false);
        //这里是设置延迟队列，如不安装插件，就会启动报错
        directExchange.setDelayed(true);
        return directExchange;
//        // 使用自定义交换器
//        Map<String, Object> props = new HashMap<>(2);
//        props.put("x-delayed-type", ExchangeTypes.FANOUT);
//        return new CustomExchange(RabbitMQConstant.EXCHANGE_DELAY, "x-delayed-message", true, false, props);
    }

}
