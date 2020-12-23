package com.example.maven.springbootlearn.controller;

import javax.annotation.Resource;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.zaxxer.hikari.HikariConfig;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("json")
public class JSONController {

    @Resource
    HikariConfig cc;

    @RequestMapping("param/{id}")
    public Object testOneParams(@PathVariable int id) {
        System.out.println(id);
        return id;
    }

    @RequestMapping("cc")
    public Object cc() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        // 反序列化时忽略不存在的JavaBean属性: SerializationFeature.FAIL_ON_EMPTY_BEANS
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, true);
        System.out.println(mapper.writeValueAsString(cc));
        return "ok";
    }

}
