package com.dreambuilders.hystrix.controller;

import com.dreambuilders.hystrix.model.Student;
import com.dreambuilders.hystrix.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StudentController {

    private StudentService studentService;

    @Autowired
    StudentController(StudentService studentService)
    {
        this.studentService = studentService;
    }

    @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getStudent()
    {
        Student student = studentService.getStudent();
        return new ResponseEntity<>(student, HttpStatus.OK);
    }
}
