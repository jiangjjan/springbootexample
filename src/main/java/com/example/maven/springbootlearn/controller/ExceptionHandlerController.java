package com.example.maven.springbootlearn.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController                               
@RequestMapping("exception")
public class ExceptionHandlerController {                                                                           

    @GetMapping("create/{exType}")
    public Object createException(@PathVariable String exType) throws Exception {

        if ("a".equals(exType))
            throw new AException("this a exception");
        if ("b".equals(exType))
            throw new BException("this b exception");
        if ("g".equals(exType))
            throw new Exception("this is global ex");

        return "ok";
    }

    @ExceptionHandler(AException.class)
    public Object handException(HttpServletRequest req, AException ex) {
        System.out.println(ex);
        return "10001";
    }

}

class BException extends Exception {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    BException(String message) {
        super(message);
    }

}

class AException extends Exception {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    AException(String message) {
        super(message);
    }

}
