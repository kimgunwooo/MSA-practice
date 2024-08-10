package com.sparta.msa_exam.product.core.service;

import java.util.List;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.sparta.msa_exam.product.core.domain.Product;
import com.sparta.msa_exam.product.application.port.in.ProductRequest;
import com.sparta.msa_exam.product.application.port.in.ProductUseCase;
import com.sparta.msa_exam.product.application.port.out.ProductPersistencePort;
import com.sparta.msa_exam.product.application.port.out.ProductResponse;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ProductService implements ProductUseCase {

	private final ProductPersistencePort productPersistencePort;

	@CacheEvict(cacheNames = "getProductAllCache", allEntries = true)
	@Override
	public void createProduct(ProductRequest productRequest) {
		Product product = Product.builder()
			.name(productRequest.name())
			.supplyPrice(productRequest.supplyPrice())
			.build();

		productPersistencePort.create(product);
	}

	@Cacheable(cacheNames = "getProductAllCache", key = "methodName")
	@Override
	public List<ProductResponse> getProducts() {
		return productPersistencePort.readAll()
			.stream()
			.map(ProductResponse::of)
			.toList();
	}
}
