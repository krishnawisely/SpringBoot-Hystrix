package com.dreambuilders.hystrix.service;

import com.dreambuilders.hystrix.model.Student;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
public class StudentService {
    @HystrixCommand(fallbackMethod = "invalid",
    		commandProperties = {
    				@HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds", value = "1000")
    		})
    public Student getStudent()
    {
        final String uri = "https://api.myjson.com/bins/eow68";
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(uri,Student.class);
    }
    public Student invalid()
    {
        Student student = new Student();
        student.setName("fallback");
        student.setEmail("fallback");
        student.setPassword("fallback");
        log.error("Unable to retrieve student details");
        return student;
    }
}
