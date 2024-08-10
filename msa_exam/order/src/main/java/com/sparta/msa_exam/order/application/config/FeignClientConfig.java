package com.sparta.msa_exam.order.application.config;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

@EnableFeignClients("com.sparta.msa_exam")
@Configuration
public class FeignClientConfig {
}
