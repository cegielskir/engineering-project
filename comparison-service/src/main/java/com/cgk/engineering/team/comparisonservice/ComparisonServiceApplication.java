package com.cgk.engineering.team.comparisonservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableDiscoveryClient
@EnableSwagger2
public class ComparisonServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ComparisonServiceApplication.class, args);
	}

}
