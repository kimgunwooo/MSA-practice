package com.sparta.msa_exam.product.application.port.in;

public record ProductRequest(
	String name,
	int supplyPrice
) {
}
