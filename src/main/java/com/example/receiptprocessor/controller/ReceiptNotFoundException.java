package com.example.receiptprocessor.controller;

public class ReceiptNotFoundException extends RuntimeException {
    public ReceiptNotFoundException(String message) {
        super(message);
    }

    public ReceiptNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public ReceiptNotFoundException(Throwable cause) {
        super(cause);
    }
}
