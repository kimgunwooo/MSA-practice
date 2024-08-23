package com.sparta.msa_exam.order.application.port.in;

import com.sparta.msa_exam.order.application.port.out.OrderResponse;

public interface OrderUseCase {
	OrderResponse createOrder(OrderRequest orderRequest);

	void addProductToOrder(Long orderId, AddProductRequest addProductRequest);

	OrderResponse getOrder(Long orderId);
}
