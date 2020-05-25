package com.amsidh.mvc.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class EncryptedPasswordUtil {

    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public EncryptedPasswordUtil(BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public String encrypt(String plainPassword) {
        return bCryptPasswordEncoder.encode(plainPassword);
    }

}
