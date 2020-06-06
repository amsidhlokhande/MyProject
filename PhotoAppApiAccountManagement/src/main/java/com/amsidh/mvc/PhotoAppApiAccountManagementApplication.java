package com.amsidh.mvc;

import com.amsidh.mvc.config.SwaggerConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.core.env.Environment;

import static java.lang.String.format;


@Slf4j
@SpringBootApplication
@EnableDiscoveryClient
public class PhotoAppApiAccountManagementApplication implements CommandLineRunner {

	@Autowired
	private Environment environment;

	public static void main(String[] args) {
		Class[] classes = {PhotoAppApiAccountManagementApplication.class, SwaggerConfig.class};
		SpringApplication.run(classes, args);
	}

	@Override
	public void run(String... args) throws Exception {
		log.info(format("Log File Location %s", environment.getProperty("logging.file")));
	}
}

