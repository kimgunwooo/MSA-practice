package com.market.order;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OrderApplicationQueueConfig {

    @Value("${message.exchange}")
    private String exchange;

    @Value("${message.queue.product}")
    private String queueProduct;

    @Value("${message.queue.payment}")
    private String queuePayment;

    @Bean
    public TopicExchange exchange() {
        return new TopicExchange(exchange); // 해당 이름의 exchange 가 생성됨.
    }

    @Bean
    public Queue queueProduct() {
        return new Queue(queueProduct); // 해당 이름의 Queue 가 생성 됨.
    }

    @Bean
    public Queue queuePayment() {
        return new Queue(queuePayment);
    }

    @Bean
    public Binding bindingProduct() {
        return BindingBuilder
                .bind(queueProduct()) // 바인딩할 큐
                .to(exchange()) // exchange
                .with(queueProduct); // 라우팅 이름 (큐의 이름과 일치)
    }

    @Bean
    public Binding bindingPayment() {
        return BindingBuilder.bind(queuePayment()).to(exchange()).with(queuePayment);
    }
}
