package com.qjc.mq.callbackconfig;

import com.qjc.mq.constant.RabbitMQConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

/**
 * @Description:
 * @Author: qjc
 * @Date: 2021/12/7
 */
@Component
@Slf4j
public class MsgSendReturnCallback implements RabbitTemplate.ReturnCallback {

    /**
     * 使用该功能需要开启消息返回确认，yml需要配置 publisher-returns: true
     * message    消息主体 message
     * replyCode  消息主体 message
     * replyText  描述
     * exchange   消息使用的交换机
     * routingKey 消息使用的路由键
     * <p>
     * PS：通过实现ReturnCallback接口，如果消息从交换机发送到对应队列失败时触发
     * </p>
     */
    @Override
    public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {
        if (exchange.equals(RabbitMQConstant.EXCHANGE_DELAY)) {
            // 如果配置了发送回调ReturnCallback，rabbitmq_delayed_message_exchange插件会回调该方法，因为发送方确实没有投递到队列上，只是在交换器上暂存，等过期/时间到了才会发往队列。
            // 所以如果是延迟队列的交换器，则直接放过，并不是bug
            return;
        }
        String correlationId = message.getMessageProperties().getCorrelationId();
        log.debug("消息：{} 发送失败, 应答码：{} 原因：{} 交换机: {}  路由键: {}", correlationId, replyCode, replyText, exchange, routingKey);
    }
}
