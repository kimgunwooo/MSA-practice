package com.sparta.msa_exam.product.application.port.out;

import java.util.List;

import com.sparta.msa_exam.product.core.domain.Product;

public interface ProductPersistencePort {

	void create(Product product);

	List<Product> readAll();
}
