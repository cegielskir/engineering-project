package com.cgk.engineering.team.simpleclient;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableFeignClients(basePackages = {"com.cgk.engineering.team.simpleclient.client"})
@SpringBootApplication
@EnableDiscoveryClient
@EnableSwagger2
public class SimpleclientApplication {

	public static void main(String[] args) {
		SpringApplication.run(SimpleclientApplication.class, args);
	}

}
