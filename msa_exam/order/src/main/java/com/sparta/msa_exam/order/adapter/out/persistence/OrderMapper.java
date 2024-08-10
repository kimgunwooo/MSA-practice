package com.sparta.msa_exam.order.adapter.out.persistence;

import com.sparta.msa_exam.order.core.domain.Order;

public class OrderMapper {

	public static Order entityToDomain(OrderJpaEntity orderJpaEntity) {
		return Order.builder()
			.id(orderJpaEntity.getId())
			.name(orderJpaEntity.getName())
			.product_ids(orderJpaEntity.getProductIds())
			.build();
	}

	public static OrderJpaEntity domainToEntity(Order order) {
		return OrderJpaEntity.builder()
			.id(order.getId())
			.name(order.getName())
			.productIds(order.getProduct_ids())
			.build();
	}
}
