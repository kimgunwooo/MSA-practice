package com.study.reactor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ReactorApplication {

	public static void main(String[] args) {
		var publisher = new Publisher();
//		publisher.startFlux()
//				.subscribe(System.out::println);

		publisher.startMono()
				.subscribe();

		publisher.startMono2()
				.subscribe();
	}


}
