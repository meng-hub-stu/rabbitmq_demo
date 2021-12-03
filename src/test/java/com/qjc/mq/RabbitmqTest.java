package com.qjc.mq;

import com.qjc.mq.mqsender.direct.DirectSender;
import com.qjc.mq.mqsender.fanout.FanoutSender;
import com.qjc.mq.mqsender.topic.TopicSender;
import com.qjc.mq.mqsender.work.WorkSender;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @ClassName: RabbitmqTest
 * @Description:
 * @Author: qjc
 * @Date: 2021/12/3 3:23 下午
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = RabbitmqDemoApplication.class)
public class RabbitmqTest {

    @Resource
    DirectSender directSender;
    @Resource
    WorkSender workSender;
    @Resource
    TopicSender topicSender;
    @Resource
    FanoutSender fanoutSender;

    @Test
    public void testDirectSendMessage() {
        for (int i = 0; i < 10; i++) {
            directSender.send(i);
        }
    }

    @Test
    public void testWorkSendMessage() {
        for (int i = 0; i < 10; i++) {
            workSender.send(i);
        }
    }

    @Test
    public void testTopicSendMessage() {
        for (int i = 0; i < 10; i++) {
            topicSender.send(i);
        }
    }

    @Test
    public void testFanoutSendMessage() {
        for (int i = 0; i < 10; i++) {
            fanoutSender.send(i);
        }
    }

}
