package com.example.dom.exception;

public class DeviceAlreadyExistException extends RuntimeException {
    public DeviceAlreadyExistException(String message) {
        super(message);
    }
}