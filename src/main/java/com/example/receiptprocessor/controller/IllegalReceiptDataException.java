package com.example.receiptprocessor.controller;

public class IllegalReceiptDataException extends RuntimeException {
    public IllegalReceiptDataException(String message) {
        super(message);
    }
}
