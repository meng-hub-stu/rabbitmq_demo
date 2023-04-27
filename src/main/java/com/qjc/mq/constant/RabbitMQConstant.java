package com.qjc.mq.constant;

/**
 * @Description: RabbitMQ常量
 * @Author: qjc
 * @Date: 2021/12/6
 */
public class RabbitMQConstant {

    /**
     * Direct交换机
     */
    public static final String EXCHANGE_DIRECT = "direct_exchange";
    /**
     * Work交换机
     */
    public static final String EXCHANGE_WORK = "work_exchange";
    /**
     * Topic交换机
     */
    public static final String EXCHANGE_TOPIC = "topic_exchange";
    /**
     * Fanout交换机
     */
    public static final String EXCHANGE_FANOUT = "fanout_exchange";
    /**
     * Direct交换机：ttl超时交换器
     */
    public static final String EXCHANGE_TTL = "ttl_exchange";
    /**
     * Direct交换机：dlx死信交换器
     */
    public static final String EXCHANGE_DLX = "dlx_exchange";
    /**
     * Direct交换机：用来测试死信队列而创建的正常交换器
     */
    public static final String EXCHANGE_DLX_NORMAL = "dlx_exchange_normal";
    /**
     * 延迟交换器 PS：安装rabbitmq_delayed_message_exchange插件，使用延迟队列
     */
    public static final String EXCHANGE_DELAY = "delay_exchange";


    /**
     * 路由(Direct)模式
     */
    public static final String QUEUE_DIRECT = "direct.queue";
    /**
     * Work模式
     */
    public static final String QUEUE_WORK = "work.queue";
    /**
     * 主题(Topic)模式
     */
    public static final String QUEUE_TOPIC_FIRST = "topic.queue.first";
    public static final String QUEUE_TOPIC_SECOND = "topic.queue.second";
    /**
     * 发布订阅/广播(Fanout)模式：不需要路由key
     */
    public static final String QUEUE_FANOUT_FIRST = "fanout.queue.first";
    public static final String QUEUE_FANOUT_SECOND = "fanout.queue.second";
    /**
     * 超时队列
     */
    public static final String QUEUE_TTL = "ttl.queue";
    /**
     * 死信队列
     */
    public static final String QUEUE_DLX = "dlx.queue";
    /**
     * 用来测试死信队列而创建的正常队列
     */
    public static final String QUEUE_DLX_NORMAL = "dlx.queue.normal";
    /**
     * 延迟队列 PS：安装rabbitmq_delayed_message_exchange插件，使用延迟队列
     */
    public static final String QUEUE_DELAY = "delay.queue";


    /**
     * 路由(Direct)模式路由key
     */
    public static final String ROUTING_KEY_DIRECT = "routing.key.direct";
    /**
     * Work模式路由key
     */
    public static final String ROUTING_KEY_WORK = "routing.key.work";
    /**
     * 主题(Topic)模式路由key：除了一下两个路由的日志，其他的日志比如sh.info.log都会发送失败
     */
    /**
     * 主题(Topic)模式路由key：北京服务器所有的日志
     */
    public static final String ROUTING_KEY_TOPIC_BJ_LOG = "bj.#";
    /**
     * 主题(Topic)模式路由key：所有error级别的日志
     */
    public static final String ROUTING_KEY_TOPIC_ERROR_LOG = "*.error.log";
    /**
     * 超时队列路由key
     */
    public static final String ROUTING_KEY_TTL = "routing.key.ttl";
    /**
     * 死信队列路由key
     */
    public static final String ROUTING_KEY_DLX = "routing.key.dlx";
    /**
     * 用来测试死信队列而创建的正常路由key
     */
    public static final String ROUTING_KEY_DLX_NORMAL = "routing.key.dlx.normal";
    /**
     * 延迟队列路由key PS：安装rabbitmq_delayed_message_exchange插件，使用延迟队列
     */
    public static final String ROUTING_KEY_DELAY = "routing.key.delay";


}
