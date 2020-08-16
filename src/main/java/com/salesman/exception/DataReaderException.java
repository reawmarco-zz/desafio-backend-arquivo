package com.salesman.exception;

public class DataReaderException extends RuntimeException {

    private static final long serialVersionUID = -4904860106364227933L;

    public DataReaderException(String message) {
        super(message);
    }

    public DataReaderException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
