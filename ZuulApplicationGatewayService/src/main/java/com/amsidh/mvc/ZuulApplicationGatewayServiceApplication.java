package com.amsidh.mvc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.core.env.Environment;


@SpringBootApplication
@EnableEurekaClient
@EnableZuulProxy
public class ZuulApplicationGatewayServiceApplication implements CommandLineRunner {

	@Autowired
	private Environment environment;

	public static void main(String[] args) {
		SpringApplication.run(ZuulApplicationGatewayServiceApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("Read Property 'api.users-ws.actuator.url.path':"+ this.environment.getProperty("api.users-ws.actuator.url.path"));
	}
}
