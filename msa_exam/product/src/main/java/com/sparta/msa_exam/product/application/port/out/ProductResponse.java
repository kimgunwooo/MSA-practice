package com.sparta.msa_exam.product.application.port.out;

import com.sparta.msa_exam.product.core.domain.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;

@Getter
@AllArgsConstructor
public class ProductResponse implements Serializable{ // Redis 직렬화를 위해 record 타입 사용 불가
	Long productId;
	String name;
	int supplyPrice;

	public static ProductResponse of(Product product) {
		return new ProductResponse(
			product.getId(),
			product.getName(),
			product.getSupplyPrice());
	}
}
