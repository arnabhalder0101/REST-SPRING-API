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


}


