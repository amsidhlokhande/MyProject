package com.amsidh.mvc;

import com.amsidh.mvc.swagger.SpringFoxConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class PhotoAppApiAccountManagementApplication {
	public static void main(String[] args) {
		Class[] classes = {PhotoAppApiAccountManagementApplication.class, SpringFoxConfig.class};
		SpringApplication.run(classes, args);
	}

}
