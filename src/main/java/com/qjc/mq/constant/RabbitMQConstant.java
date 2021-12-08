package com.qjc.mq.constant;

/**
 * @Description: RabbitMQ常量
 * @Author: qjc
 * @Date: 2021/12/6
 */
public class RabbitMQConstant {

    /**
     * 简单模式
     */
    public static final String QUEUE_EASY = "easy.queue";
    /**
     * work模式
     */
    public static final String QUEUE_WORK = "work.queue";
    /**
     * topic模式
     */
    public static final String QUEUE_TOPIC_FIRST = "topic.queue.first";
    public static final String QUEUE_TOPIC_SECOND = "topic.queue.second";
    /**
     * 发布订阅模式
     */
    public static final String QUEUE_FANOUT = "fanout.queue";
    public static final String QUEUE_FANOUT_SECOND = "fanout.queue.second";
    /**
     * 超时队列
     */
    public static final String QUEUE_TTL = "ttl.queue";
    /**
     * 死信队列
     */
    public static final String QUEUE_DLX = "dlx.queue";
    public static final String QUEUE_DLX_NORMAL = "dlx.queue.normal";

    /**
     * 路由key
     */
    public static final String ROUTING_KEY_EASY = "routing.key.easy";
    public static final String ROUTING_KEY_WORK = "routing.key.work";
    /**
     * 除了一下两个路由的日志，其他的日志比如sh.info.log都会发送失败
     */
    /**
     * 北京服务器所有的日志
     */
    public static final String ROUTING_KEY_TOPIC_BJ_LOG = "bj.#";
    /**
     * 所有error级别的日志
     */
    public static final String ROUTING_KEY_TOPIC_ERROR_LOG = "*.error.log";
    public static final String ROUTING_KEY_TTL = "routing.key.ttl";
    public static final String ROUTING_KEY_DLX = "routing.key.dlx";
    public static final String ROUTING_KEY_DLX_NORMAL = "routing.key.dlx.normal";

    /**
     * direct交换机
     */
    public static final String EXCHANGE_DIRECT = "direct_exchange";
    /**
     * work交换机
     */
    public static final String EXCHANGE_WORK = "work_exchange";
    /**
     * topic交换机
     */
    public static final String EXCHANGE_TOPIC = "topic_exchange";
    /**
     * fanout交换机
     */
    public static final String EXCHANGE_FANOUT = "fanout_exchange";
    /**
     * ttl交换器
     */
    public static final String EXCHANGE_TTL = "ttl_exchange";
    /**
     * 死信交换器
     */
    public static final String EXCHANGE_DLX = "dlx_exchange";
    public static final String EXCHANGE_DLX_NORMAL = "dlx_exchange_normal";

}
