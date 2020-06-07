package com.amsidh.mvc;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class CloudConfigWSApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(CloudConfigWSApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
	}
}
