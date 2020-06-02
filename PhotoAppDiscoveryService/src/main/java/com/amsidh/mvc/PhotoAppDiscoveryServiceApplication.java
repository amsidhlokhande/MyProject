package com.amsidh.mvc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

import static org.springframework.boot.SpringApplication.run;


@SpringBootApplication
@EnableEurekaServer
public class PhotoAppDiscoveryServiceApplication {

    public static void main(String[] args) {
        run(PhotoAppDiscoveryServiceApplication.class, args);
    }

}
