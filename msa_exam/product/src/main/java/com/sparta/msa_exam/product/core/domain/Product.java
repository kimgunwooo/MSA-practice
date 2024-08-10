package com.sparta.msa_exam.product.core.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class Product {
	private Long id;
	private String name;
	private int supplyPrice;
}
