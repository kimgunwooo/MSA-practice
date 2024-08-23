package com.sparta.msa_exam.product.application.port.in;

import java.util.List;

import com.sparta.msa_exam.product.application.port.out.ProductResponse;

public interface ProductUseCase {
	void createProduct(ProductRequest productRequest);

	List<ProductResponse> getProducts();
}
