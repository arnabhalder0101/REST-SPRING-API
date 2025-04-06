package com.arnab.rest_api_demo.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class StudentRestExceptionHandler {

    // add an exception handler
    @ExceptionHandler
    // This guy here is Catching *1
    public ResponseEntity<StudentErrorResponse> handleException(StudentNotFoundException exc){
        StudentErrorResponse error = new StudentErrorResponse();
        error.setStatus(HttpStatus.NOT_FOUND.value());
        error.setMessage(exc.getMessage());
        error.setTimestamp(System.currentTimeMillis());
        return  new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    // add another exception handler to catch any kind of error --
    /*
    If any other type of exception happens this guy below here will catch that--
    */
    @ExceptionHandler
    public ResponseEntity<StudentErrorResponse> handleException(Exception exc){
        StudentErrorResponse error = new StudentErrorResponse();
        error.setStatus(HttpStatus.BAD_REQUEST.value());
        error.setMessage(exc.getMessage());
        error.setTimestamp(System.currentTimeMillis());

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
/*
    @ExceptionHandler
    public  ResponseEntity<StudentErrorResponse> handleException(StudentIdConversionException exc){

        StudentErrorResponse err = new StudentErrorResponse();
        err.setTimestamp(System.currentTimeMillis());
        err.setMessage(exc.getMessage());
        err.setStatus(404);

        return  new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);
    }
*/

}
