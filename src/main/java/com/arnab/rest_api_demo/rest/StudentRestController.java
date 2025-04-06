package com.arnab.rest_api_demo.rest;

import com.arnab.rest_api_demo.entity.Student;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class StudentRestController {

    private List<Student> students;

    // constructor -- @PostConstruct
    @PostConstruct
    public void loadData() {
        students = new ArrayList<>();
        students.add(new Student("Arnab", "Halder"));
        students.add(new Student("Rohit", "Sharma"));
        students.add(new Student("John", "Dorie"));
    }

    // @PreDestroy to clean the array
    @PreDestroy
    public void cleardata(){
        System.out.println(students.size());
        System.out.println("Clearing the array.");
        this.students.clear();
        System.out.println(students.size());
    }

    // define a endpoint for "/students"

    @GetMapping("/students")
    public List<Student> getStudents() {

        return this.students;
    }

    @GetMapping("/students/{studentId}")
    public Student getStudent(@PathVariable int studentId) {

      /*  if(studentId==9){
            throw new StudentIdConversionException("Number 9 is not Accepted and not able to convert in anything...");
        }*/
        // check the stID --
        if(studentId>=students.size() || studentId<0){
            // throwing  StudentNotFoundException Type of Exception *1
            throw new StudentNotFoundException("Student with the Id "+studentId+" not Found");
        }
        return this.students.get(studentId);
    }

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


