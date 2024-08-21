package com.market.order;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Slf4j
@RestController
@RequiredArgsConstructor
public class OrderEndpoint {

    private final OrderService orderService;

    @GetMapping("/order/{orderId}")
    public ResponseEntity<Order> getOrder(@PathVariable UUID orderId) {
        Order order = orderService.getOrder(orderId);
        return ResponseEntity.ok(order);
    }

    @PostMapping("/order")
    public ResponseEntity<Order> order(@RequestBody OrderRequestDto orderRequestDto) {
        Order order = orderService.createOrder(orderRequestDto);
        return ResponseEntity.ok(order);
    }

    @RabbitListener(queues = "${message.queue.err.order}")
    public void errOrder(DeliveryMessage message) {
        log.info("ERROR RECEIVE !!!");
        orderService.rollbackOrder(message);
    }

    @Data
    public static class OrderRequestDto {
        private String userId;
        private Integer productId;
        private Integer productQuantity;
        private Integer payAmount;

        public Order toOrder (){
            return Order.builder()
                    .orderId(UUID.randomUUID())
                    .userId(userId)
                    .orderStatus("RECEIPT")
                    .build();

        }

        public DeliveryMessage toDeliveryMessage(UUID orderId){
            return DeliveryMessage.builder()
                    .orderId(orderId)
                    .userId(userId)
                    .productId(productId)
                    .productQuantity(productQuantity)
                    .payAmount(payAmount)
                    .build();
        }
    }

}
