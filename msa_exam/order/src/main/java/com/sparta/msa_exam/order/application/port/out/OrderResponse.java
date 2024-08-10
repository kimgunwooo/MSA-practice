package com.sparta.msa_exam.order.application.port.out;

import com.sparta.msa_exam.order.core.domain.Order;

import java.util.List;

public record OrderResponse(
        Long orderId,
        String name,
        List<Long> product_ids
) {
    public static OrderResponse of(Order order) {
        return new OrderResponse(
                order.getId(),
                order.getName(),
                order.getProduct_ids()
        );
    }
}
