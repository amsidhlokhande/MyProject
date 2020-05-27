package com.amsidh.mvc;

import com.amsidh.mvc.security.SpringSecurityConfig;
import com.amsidh.mvc.swagger.SpringFoxConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Slf4j
@SpringBootApplication
@EnableJpaRepositories(basePackages = {"com.amsidh.mvc.repository"})
@EnableDiscoveryClient
public class PhotoAppApiUsersApplication {

    public static void main(String[] args) {
        Class[] classes = {PhotoAppApiUsersApplication.class};
        SpringApplication.run(classes, args);
    }

    @Bean
    public BCryptPasswordEncoder getBCryptPasswordEncoder() {
       log.info("getBCryptPasswordEncoder method is called!!!");
        return new BCryptPasswordEncoder();
    }
}
