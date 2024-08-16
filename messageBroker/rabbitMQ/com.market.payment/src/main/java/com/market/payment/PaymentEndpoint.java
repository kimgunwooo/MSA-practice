package com.market.payment;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Value;

@Slf4j
@Component
public class PaymentEndpoint { // 사용자의 입력이 아닌, case 인 경우 Endpoint 로 설정

    @Value("${spring.application.name}")
    private String appName;

    @RabbitListener(queues = "${message.queue.payment}")
    public void receiveMessage(String orderId) {
        log.info("receive orderId:{}, appName : {}", orderId, appName);
    }
}