package com.example.maven.springbootlearn.filter_demo;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("filter")
public class FilterController {

    @RequestMapping("test")
    public Object test() {
        return "ok";
    }
    @RequestMapping("demo2")
    public Object test2Object() {
        return "ok";
    }

}
