package com.amsidh.mvc.config;

import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.client.RestTemplate;

import static org.modelmapper.convention.MatchingStrategies.STRICT;

@Slf4j
@Configuration
public class AppConfig {

    @Bean
    public ModelMapper getModelMapper() {
        log.info("getModelMapper method is called!!!");
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration()
                .setMatchingStrategy(STRICT);
        return modelMapper;
    }

    @Bean
    public BCryptPasswordEncoder getBCryptPasswordEncoder() {
        log.info("getBCryptPasswordEncoder method is called!!!");
        return new BCryptPasswordEncoder();
    }

    @LoadBalanced
    @Bean
    public RestTemplate getRestTemplate() {
        log.info("getRestTemplate method is called!!!");
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate;
    }
}
