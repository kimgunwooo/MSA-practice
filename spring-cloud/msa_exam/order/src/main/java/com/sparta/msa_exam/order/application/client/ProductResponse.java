package com.sparta.msa_exam.order.application.client;

public record ProductResponse(
	Long product_id,
	String name,
	int supplyPrice
) {
}
