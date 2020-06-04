package com.amsidh.mvc;

import com.amsidh.mvc.config.SwaggerConfig;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import static org.springframework.boot.SpringApplication.run;

@SpringBootApplication
@EnableDiscoveryClient
public class PhotoAppApiAccountManagementApplication {
	public static void main(String[] args) {
		Class[] classes = {PhotoAppApiAccountManagementApplication.class, SwaggerConfig.class};
		run(classes, args);
	}

}
