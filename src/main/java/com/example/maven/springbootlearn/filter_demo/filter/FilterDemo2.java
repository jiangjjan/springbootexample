package com.example.maven.springbootlearn.filter_demo.filter;

import java.io.IOException;
import java.lang.reflect.Field;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class FilterDemo2 implements Filter {

    @Override
    public void doFilter(ServletRequest req, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
                
        System.out.println("==========================");
        Class<? extends ServletRequest> clzz = req.getClass();
        Field[] privateFields = clzz.getDeclaredFields();
        Field[] fields = clzz.getFields();
        try {
            printField(req, privateFields);
            printField(req, fields);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        System.out.println("==========================");

    }

    private void printField(ServletRequest req, Field[] fields)
            throws IllegalArgumentException, IllegalAccessException {

        for (Field e : fields) {
            e.setAccessible(true);

            System.out.println(String.format("%s || %s", e.getName(), e.get(req)));
        }
    }

}
