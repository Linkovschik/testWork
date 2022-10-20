package com.example.testWork.exceptions;

public class NotFoundOrderException extends RuntimeException {

    public NotFoundOrderException() {

        super("No order found");
    }
}