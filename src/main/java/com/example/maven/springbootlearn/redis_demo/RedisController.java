package com.example.maven.springbootlearn.redis_demo;

import com.example.maven.springbootlearn.mybatis_demo.entity.Student;
import com.example.maven.springbootlearn.redis_demo.service.QueryStudentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.RedisConnectionFailureException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

@RestController
@RequestMapping("redis")
public class RedisController {

    @Autowired
    QueryStudentService service;

 
    @GetMapping("student")
    public Object getStudent() {
       return  service.getStudentS();
    }

    @PostMapping("student")
    public Object addStudent(@RequestBody Student student) {
       return  service.addStudent(student);
    }

    @ExceptionHandler(RedisConnectionFailureException.class)
    public Object redisConnectFail(WebRequest request){
               return request.getParameterMap();
    }


}