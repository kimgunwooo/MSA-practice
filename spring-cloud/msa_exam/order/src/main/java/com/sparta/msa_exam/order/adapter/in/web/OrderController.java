package com.sparta.msa_exam.order.adapter.in.web;

import com.sparta.msa_exam.order.application.port.in.AddProductRequest;
import com.sparta.msa_exam.order.application.port.out.OrderResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.sparta.msa_exam.order.application.port.in.OrderRequest;
import com.sparta.msa_exam.order.application.port.in.OrderUseCase;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderUseCase orderUseCase;

    @PostMapping
    public ResponseEntity<OrderResponse> createOrder(@RequestBody OrderRequest orderRequest) {
		OrderResponse response = orderUseCase.createOrder(orderRequest);

        return ResponseEntity.ok(response);
    }

    @PutMapping("/{orderId}")
    public ResponseEntity<Void> addProductToOrder(@PathVariable Long orderId,
												  @RequestBody AddProductRequest addProductRequest) {
		orderUseCase.addProductToOrder(orderId, addProductRequest);
		return ResponseEntity.ok().build();
    }

	@GetMapping("/{orderId}")
	public ResponseEntity<OrderResponse> getOrder(@PathVariable Long orderId) {
		OrderResponse response = orderUseCase.getOrder(orderId);
		return ResponseEntity.ok(response);
	}
}
