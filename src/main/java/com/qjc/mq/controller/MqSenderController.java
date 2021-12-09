package com.qjc.mq.controller;

import com.qjc.mq.mqsender.delay.DelaySender;
import com.qjc.mq.mqsender.direct.DirectSender;
import com.qjc.mq.mqsender.dlx.DLXNormalSender;
import com.qjc.mq.mqsender.fanout.FanoutSender;
import com.qjc.mq.mqsender.topic.TopicSender;
import com.qjc.mq.mqsender.ttl.TTLSender;
import com.qjc.mq.mqsender.work.WorkSender;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;


/**
 * @Description:
 * @Author: qjc
 * @Date: 2021/12/6
 */
@RestController
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE, RequestMethod.PUT})
public class MqSenderController {

    /**
     * rabbitMQ 的核心就是  交换器,路由键,队列;
     * <p>
     * 消息来了之后,会发送到交换器,交换器将根据路由键将消息发送到相对应的队列里面去!
     * <p>
     * 消息不用关系到达了那个队列.只需要带着自己的路由键到了交换器就可以了,交换器会帮你把消息发送到指定的队列里面去!
     */

    @Resource
    DirectSender directSender;
    @Resource
    TTLSender ttlSender;
    @Resource
    DLXNormalSender dlxNormalSender;
    @Resource
    WorkSender workSender;
    @Resource
    TopicSender topicSender;
    @Resource
    FanoutSender fanoutSender;
    @Resource
    DelaySender delaySender;

    /**
     * 测试简单模式： http://localhost:8899/direct
     */
    @RequestMapping(value = "/direct", method = {RequestMethod.GET})
    public void directSender() {
        for (int i = 0; i < 1; i++) {
            directSender.send(i);
        }
    }

    /**
     * 测试简单模式-消息&队列TTL： http://localhost:8899/ttl
     */
    @RequestMapping(value = "/ttl", method = {RequestMethod.GET})
    public void directTTLSender() {
        for (int i = 0; i < 10; i++) {
            ttlSender.send(i);
        }
    }

    /**
     * 测试简单模式-DLX： http://localhost:8899/dlx/20
     */
    @RequestMapping(value = "/dlx/{seconds}", method = {RequestMethod.GET})
    public String directDLXSender(@PathVariable Integer seconds) {
        return dlxNormalSender.send(seconds);
    }

    /**
     * 测试work模式： http://localhost:8899/work
     */
    @RequestMapping(value = "/work", method = {RequestMethod.GET})
    public void workSender() {
        for (int i = 0; i < 10; i++) {
            workSender.send(i);
        }
    }

    /**
     * 测试topic模式： http://localhost:8899/topic
     */
    @RequestMapping(value = "/topic", method = {RequestMethod.GET})
    public void topicSender() {
        for (int i = 0; i < 10; i++) {
            topicSender.send(i);
        }
    }

    /**
     * 测试fanout模式： http://localhost:8899/fanout
     */
    @RequestMapping(value = "/fanout", method = {RequestMethod.GET})
    public void fanoutSender() {
        for (int i = 0; i < 10; i++) {
            fanoutSender.send(i);
        }
    }

    /**
     * 测试fanout模式： http://localhost:8899/delay/20
     * PS：安装rabbitmq_delayed_message_exchange插件，使用延迟队列
     *
     * @param seconds 多少秒后开会
     */
    @RequestMapping(value = "/delay/{seconds}", method = {RequestMethod.GET})
    public String delaySender(@PathVariable Integer seconds) {
        return delaySender.send(seconds);
    }


}
