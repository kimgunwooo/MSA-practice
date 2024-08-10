package com.sparta.msa_exam.order.application.port.out;

import com.sparta.msa_exam.order.core.domain.Order;
import com.sparta.msa_exam.order.application.port.in.AddProductRequest;

public interface OrderPersistencePort {

	Order create(Order order);

	void addProduct(Long orderId, AddProductRequest addProductRequest);

	Order read(Long orderId);
}
