package com.gdufe.cs.works.config;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

/**
 * @Author: wzq
 * @Description: TODO
 * @DateTime: 2022/5/9 19:30
 **/
@Configuration
public class RabbitTemplateConfig {
    @Autowired
    RabbitTemplate rabbitTemplate;

    /*
     * 定制RabbitTemplate
     * */
    @PostConstruct //MyRabbitConfig对象创建完成以后，执行这个方法
    public void initRabbitTemplate(){
        rabbitTemplate.setConfirmCallback(new RabbitTemplate.ConfirmCallback() {
            /**
             * 只要消息抵达broker ack就为true
             *  @param correlationData 当前消息的唯一关联数据（这是消息的唯一id）
             * @param ack 消息是否成功收到
             * @param cause 失败的原因
             * */
            @Override
            public void confirm(CorrelationData correlationData, boolean ack, String cause) {
                System.out.println("confirm.....correlationData["+ correlationData +"]==>ack["+ack+"]==>cause["+cause+"]");
            }
        });

        /**
         * 只要消息没有投递给指定的队列，就触发这个失败回调
         * @param message 哪个投递失败的消息详细信息
         * @param replyCode 回复的状态码
         * @param replyText 回复的文本内容
         * @param exchange 当时消息发送给哪个交换机
         * @param routingKey 当时消息的路由键
         * */
        rabbitTemplate.setReturnCallback(new RabbitTemplate.ReturnCallback() {
            @Override
            public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {
                System.out.println("Fail Message["+ message+"]==>replyCode[+"+replyCode+"+]==>replyText["+replyText+"]==>exchange["+exchange+"]==>routingKey["+routingKey+"]");
            }
        });
    }
}
