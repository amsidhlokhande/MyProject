package com.amsidh.mvc;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Slf4j
@SpringBootApplication
@EnableJpaRepositories(basePackages = {"com.amsidh.mvc.repository"})
@EnableDiscoveryClient
public class PhotoAppApiUsersApplication implements CommandLineRunner {

    @Autowired
    private Environment environment;

    public static void main(String[] args) {
        Class[] classes = {PhotoAppApiUsersApplication.class};
        SpringApplication.run(classes, args);
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("api.users-ws.actuator.url.path :"+environment.getProperty("api.users-ws.actuator.url.path"));
        System.out.println("api.zuul.swagger.url.path :"+environment.getProperty("api.zuul.swagger.url.path", String[].class));
        System.out.println("ip.address.security.allow :"+environment.getProperty("ip.address.security.allow"));
    }
}
