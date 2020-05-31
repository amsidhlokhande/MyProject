package com.amsidh.mvc.ui.exception;

public class DuplicateUserException extends RuntimeException {

    public DuplicateUserException(String attributeName, String attributeValue) {
        super(String.format("User already exists with %s %s",attributeName, attributeValue));
    }
}
