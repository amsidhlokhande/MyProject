package com.amsidh.mvc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

import static org.springframework.boot.SpringApplication.run;

@SpringBootApplication
@EnableEurekaClient
@EnableZuulProxy
public class ZuulApplicationGatewayServiceApplication {

	public static void main(String[] args) {
		run(ZuulApplicationGatewayServiceApplication.class, args);
	}

}
