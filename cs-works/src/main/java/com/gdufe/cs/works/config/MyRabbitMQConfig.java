package com.gdufe.cs.works.config;


import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;

/**
 * @Author: wzq
 * @Description: TODO
 * @DateTime: 2022/5/11 21:32
 **/
@Configuration
public class MyRabbitMQConfig {

    public static final String LIKED_RECOMMEND_QUEUE = "liked.recommend.queue";
    public static final String LIKED_DELAY_QUEUE = "liked.delay.queue";
    public static final String LIKED_EVENT_EXCHANGE = "liked-event-exchange";
    public static final String LIKED_CREATE_WANT = "liked.create.want";
    public static final String LIKED_RECOMMEND_WANT = "liked.recommend.want";
    /*
    * 死信队列
    * */
    @Bean
    public Queue likedDelayQueue(){
        /**
         * String name,
         * boolean durable,
         * boolean exclusive,
         * boolean autoDelete,
         * @Nullable Map<String, Object> arguments
         * */
        HashMap<String,Object> arguments = new HashMap<>();
        arguments.put("x-dead-letter-exchange",LIKED_EVENT_EXCHANGE);
        arguments.put("x-dead-letter-routing-key",LIKED_RECOMMEND_WANT);
        arguments.put("x-message-ttl",60000); //消息过期1分钟
        Queue queue = new Queue(LIKED_DELAY_QUEUE,true,false,false,arguments);

        return queue;
    }

    @Bean
    public Queue likedRecommendQueue(){
        Queue queue = new Queue(LIKED_RECOMMEND_QUEUE,true,false,false,null);
        return queue;
    }

    /*
    * TopicExchange
    * */
    @Bean
    public Exchange likedEventExchange(){

        return new TopicExchange(LIKED_EVENT_EXCHANGE,true,false);
    }

    @Bean
    public Binding likedCreateWantBinding(){
        /*
        * String destination,
        *  Binding.DestinationType destinationType,
        *  String exchange,
        * String routingKey,
        * @Nullable Map<String, Object> arguments
        * */
        return new Binding(LIKED_DELAY_QUEUE,
                Binding.DestinationType.QUEUE,
                LIKED_EVENT_EXCHANGE,
                LIKED_CREATE_WANT,
                null);
    }

    @Bean
    public Binding likedRecommendWantBinding(){
        return new Binding(LIKED_RECOMMEND_QUEUE,
                Binding.DestinationType.QUEUE,
                LIKED_EVENT_EXCHANGE,
                LIKED_RECOMMEND_WANT,
                null);
    }
}
