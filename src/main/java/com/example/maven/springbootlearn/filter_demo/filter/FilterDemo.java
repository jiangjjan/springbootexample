package com.example.maven.springbootlearn.filter_demo.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class FilterDemo implements Filter {

    @Override
    public void doFilter(ServletRequest req, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
                System.out.println("-------------------------");
                System.out.println(req.getRemoteAddr());
                System.out.println(req.getLocalAddr());
                System.out.println(req.getLocalName());
                System.out.println(req.getProtocol());
                System.out.println(req.getScheme());
                System.out.println(req.getCharacterEncoding());
                System.out.println("-------------------------");
                chain.doFilter(req, response);
    }

}