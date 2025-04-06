package com.arnab.rest_api_demo.rest;

public class StudentIdConversionException extends  RuntimeException{
    public StudentIdConversionException(String message) {
        super(message);
    }

    public StudentIdConversionException(String message, Throwable cause) {
        super(message, cause);
    }

    public StudentIdConversionException(Throwable cause) {
        super(cause);
    }
}
