package com.amsidh.mvc.util;

import static java.util.Base64.getUrlDecoder;
import static java.util.Base64.getUrlEncoder;

public class EncryptedPasswordUtil {
    public static String encrypt(String plainPassword){
        return getUrlEncoder().encodeToString(plainPassword.getBytes());
    }

    public static String decrypt(String password){
        return new String(getUrlDecoder().decode(password));
    }
}
