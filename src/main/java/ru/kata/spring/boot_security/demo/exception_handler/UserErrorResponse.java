package ru.kata.spring.boot_security.demo.exception_handler;

public class UserErrorResponse {

    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public UserErrorResponse() {

    }
}
