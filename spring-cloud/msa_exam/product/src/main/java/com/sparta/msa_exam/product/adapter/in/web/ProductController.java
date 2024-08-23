package com.sparta.msa_exam.product.adapter.in.web;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sparta.msa_exam.product.application.port.in.ProductRequest;
import com.sparta.msa_exam.product.application.port.out.ProductResponse;
import com.sparta.msa_exam.product.application.port.in.ProductUseCase;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductUseCase productUseCase;

    @PostMapping
    public ResponseEntity<Void> createProduct(@RequestBody ProductRequest productRequest) {
        productUseCase.createProduct(productRequest);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public List<ProductResponse> getProducts() {
        return productUseCase.getProducts();
    }
}
