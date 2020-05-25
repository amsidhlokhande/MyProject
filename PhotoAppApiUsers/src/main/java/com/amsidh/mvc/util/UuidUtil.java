package com.amsidh.mvc.util;

import org.springframework.stereotype.Service;

import static java.util.UUID.randomUUID;

@Service
public class UuidUtil {

    public String getNextUuid() {
        return randomUUID().toString();
    }
}
