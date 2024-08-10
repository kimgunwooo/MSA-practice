package com.sparta.msa_exam.order.core.domain;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class Order {
	private Long id;
	private String name;
	private List<Long> product_ids;
}
