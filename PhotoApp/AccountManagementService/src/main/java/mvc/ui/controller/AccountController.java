package com.amsidh.mvc.ui.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static java.lang.String.format;
import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private Environment environment;

    @GetMapping("/health/check")
    public ResponseEntity<String> healthCheck() {
        return ok()
                .body(format("Account Micro Service is up and running on port %s",environment.getProperty("local.server.port")));
    }
}
