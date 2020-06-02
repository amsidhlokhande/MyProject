package com.amsidh.mvc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import static org.springframework.boot.SpringApplication.run;

@SpringBootApplication
@EnableDiscoveryClient
public class PhotoAppApiAlbumsApplication {

    public static void main(String[] args) {
        run(PhotoAppApiAlbumsApplication.class, args);
    }

}