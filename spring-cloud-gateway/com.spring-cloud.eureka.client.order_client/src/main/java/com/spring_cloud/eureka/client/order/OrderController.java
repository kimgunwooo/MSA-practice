package com.spring_cloud.eureka.client.order;

import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequiredArgsConstructor
public class OrderController {

	private final OrderService orderService;

	@GetMapping("/order")
	public String getOrder() {
		return "OrderDetails";
	}

}
