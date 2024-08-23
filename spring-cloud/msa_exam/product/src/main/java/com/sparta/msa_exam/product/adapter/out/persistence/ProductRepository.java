package com.sparta.msa_exam.product.adapter.out.persistence;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sparta.msa_exam.product.core.domain.Product;
import com.sparta.msa_exam.product.application.port.out.ProductPersistencePort;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Transactional
@Repository
public class ProductRepository implements ProductPersistencePort {

	private final ProductJpaRepository productJpaRepository;

	@Override
	public void create(Product product) {
		productJpaRepository.save(ProductMapper.domainToEntity(product));
	}

	@Override
	public List<Product> readAll() {
		return productJpaRepository.findAll()
			.stream()
			.map(ProductMapper::entityToDomain)
			.toList();
	}
}
