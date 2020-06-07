package com.amsidh.mvc;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@SpringBootApplication
@EnableEurekaServer
public class DiscoveryServiceWSApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(DiscoveryServiceWSApplication.class, args);
    }
    @Override
    public void run(String... args) throws Exception {
    }
}
