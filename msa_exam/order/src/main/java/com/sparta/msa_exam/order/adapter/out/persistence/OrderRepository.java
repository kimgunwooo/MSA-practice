package com.sparta.msa_exam.order.adapter.out.persistence;

import com.sparta.msa_exam.order.core.domain.Order;
import com.sparta.msa_exam.order.application.port.in.AddProductRequest;
import com.sparta.msa_exam.order.application.port.out.OrderPersistencePort;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Repository
public class OrderRepository implements OrderPersistencePort {

	private final OrderJpaRepository orderJpaRepository;

	@Transactional
	@Override
	public Order create(Order order) {
		return OrderMapper.entityToDomain(orderJpaRepository.save(OrderMapper.domainToEntity(order)));
	}

	@Transactional
	@Override
	public void addProduct(Long orderId, AddProductRequest addProductRequest) {
		OrderJpaEntity order = orderJpaRepository.findById(orderId)
				.orElseThrow(() -> new RuntimeException("해당 주문이 존재하지 않습니다."));

		order.update(addProductRequest.productId());
	}

	@Transactional(readOnly = true)
	@Override
	public Order read(Long orderId) {
		return orderJpaRepository.findById(orderId)
				.map(OrderMapper::entityToDomain)
				.orElseThrow(() -> new RuntimeException("해당 주문이 존재하지 않습니다."));
	}
}
