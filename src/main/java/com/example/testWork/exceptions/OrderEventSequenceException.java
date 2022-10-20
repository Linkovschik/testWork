package com.example.testWork.exceptions;

public class OrderEventSequenceException extends RuntimeException {

    public OrderEventSequenceException(int id) {

        super("Order#"+id+" have been closed.");
    }
}
