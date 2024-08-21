package com.market.payment;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.amqp.rabbit.annotation.RabbitListener;

import lombok.RequiredArgsConstructor;

@Slf4j
@Component
@RequiredArgsConstructor
public class PaymentEndpoint {

    private final PaymentService paymentService;

    @RabbitListener(queues = "${message.queue.payment}")
    public void receiveMessage(DeliveryMessage deliveryMessage) {
        log.info("PAYMENT RECEIVE : {}", deliveryMessage.toString());
        paymentService.createPayment(deliveryMessage);

    }
}