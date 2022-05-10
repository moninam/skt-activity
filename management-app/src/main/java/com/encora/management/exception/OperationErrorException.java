package com.encora.management.exception;

public class OperationErrorException extends Exception{
    public OperationErrorException(String errorMessage) {
        super(errorMessage);
    }
}
