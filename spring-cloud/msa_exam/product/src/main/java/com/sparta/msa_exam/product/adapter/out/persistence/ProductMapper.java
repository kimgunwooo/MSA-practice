package com.sparta.msa_exam.product.adapter.out.persistence;

import com.sparta.msa_exam.product.core.domain.Product;

public class ProductMapper {

	public static Product entityToDomain(ProductJpaEntity productJpaEntity) {
		return Product.builder()
			.id(productJpaEntity.getId())
			.name(productJpaEntity.getName())
			.supplyPrice(productJpaEntity.getSupplyPrice())
			.build();
	}

	public static ProductJpaEntity domainToEntity(Product product) {
		return ProductJpaEntity.builder()
			.id(product.getId())
			.name(product.getName())
			.supplyPrice(product.getSupplyPrice())
			.build();
	}
}
