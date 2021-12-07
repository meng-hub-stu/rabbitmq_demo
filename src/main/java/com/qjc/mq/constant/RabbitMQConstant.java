package com.qjc.mq.constant;

/**
 * @Description:
 * @Author: qjc
 * @Date: 2021/12/6
 */
public class RabbitMQConstant {

    //简单模式
    public static final String QUEUE_EASY = "easy.queue";
    //work模式
    public static final String QUEUE_WORK = "work.queue";
    //topic模式
    public static final String QUEUE_TOPIC_FIRST = "topic.queue.first";
    public static final String QUEUE_TOPIC_SECOND = "topic.queue.second";
    //发布订阅模式
    public static final String QUEUE_FANOUT = "fanout.queue";
    public static final String QUEUE_FANOUT_SECOND = "fanout.queue.second";

    //路由key
    public static final String ROUTING_KEY_EASY = "routing.key.easy";
    public static final String ROUTING_KEY_WORK = "routing.key.work";
    // 除了一下两个路由的日志，其他的日志比如sh.info.log都会发送失败
    /**
     * 北京服务器所有的日志
     */
    public static final String ROUTING_KEY_TOPIC_BJ_LOG = "bj.#";
    /**
     * 所有error级别的日志
     */
    public static final String ROUTING_KEY_TOPIC_ERROR_LOG = "*.error.log";


    // direct交换机
    public static final String EXCHANGE_DIRECT = "direct_exchange";
    // work交换机
    public static final String EXCHANGE_WORK = "work_exchange";
    // topic交换机
    public static final String EXCHANGE_TOPIC = "topic_exchange";
    // fanout交换机
    public static final String EXCHANGE_FANOUT = "fanout_exchange";

}
