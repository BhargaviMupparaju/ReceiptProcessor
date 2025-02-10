package com.example.receiptprocessor.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ReceiptExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<ReceiptErrorResponse> handleIllegalReceiptException(IllegalReceiptDataException e){
        ReceiptErrorResponse errorResponse= new ReceiptErrorResponse();
        errorResponse.setStatus(HttpStatus.BAD_REQUEST.value());
        errorResponse.setMessage(e.getMessage());
        errorResponse.setTimestamp(System.currentTimeMillis());

        return new ResponseEntity<>(errorResponse,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<ReceiptErrorResponse> ReceiptNotFoundException(ReceiptNotFoundException e){
        ReceiptErrorResponse errorResponse=new ReceiptErrorResponse();
        errorResponse.setStatus(HttpStatus.NOT_FOUND.value());
        errorResponse.setMessage(e.getMessage());
        errorResponse.setTimestamp(System.currentTimeMillis());
        return new ResponseEntity<>(errorResponse,HttpStatus.NOT_FOUND);

    }



}
