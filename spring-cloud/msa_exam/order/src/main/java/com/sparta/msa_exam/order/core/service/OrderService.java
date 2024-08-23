package com.sparta.msa_exam.order.core.service;

import com.sparta.msa_exam.order.application.client.ProductClient;
import com.sparta.msa_exam.order.application.client.ProductResponse;
import com.sparta.msa_exam.order.core.domain.Order;
import com.sparta.msa_exam.order.application.port.in.AddProductRequest;
import com.sparta.msa_exam.order.application.port.in.OrderRequest;
import com.sparta.msa_exam.order.application.port.in.OrderUseCase;
import com.sparta.msa_exam.order.application.port.out.OrderPersistencePort;
import com.sparta.msa_exam.order.application.port.out.OrderResponse;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class OrderService implements OrderUseCase {

	private final OrderPersistencePort orderPersistencePort;
	private final ProductClient productClient;

	@Override
	public OrderResponse createOrder(OrderRequest orderRequest) {
		Order order = Order.builder()
				.name(orderRequest.name())
				.build();

		return OrderResponse.of(orderPersistencePort.create(order));
	}

	@Override
	public void addProductToOrder(Long orderId, AddProductRequest addProductRequest) {
		List<ProductResponse> products = productClient.getProducts();
		for (ProductResponse productResponse : products) {
			if (productResponse.product_id().equals(addProductRequest.productId())) {
				orderPersistencePort.addProduct(orderId, addProductRequest);
			}
		}
	}

	@Cacheable(cacheNames = "orderCache", key = "args[0]")
	@Override
	public OrderResponse getOrder(Long orderId) {
		return OrderResponse.of(orderPersistencePort.read(orderId));
	}
}
