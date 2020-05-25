package com.amsidh.mvc.ui.exception;

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(String userId) {
        super(String.format("No User Found with userId %s", userId));
    }
}
