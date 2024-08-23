package com.spring_cloud.eureka.client.product;

import org.springframework.web.bind.annotation.RestController;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
public class ProductController {

	@Value("${server.port}") // 애플리케이션이 실행 중인 포트를 주입받습니다.
	private String serverPort;

	@GetMapping("/product/{id}")
	public String getProduct(@PathVariable String id) {
		return "Product " + id + " info!!!!! From port : " + serverPort ;
	}


}
