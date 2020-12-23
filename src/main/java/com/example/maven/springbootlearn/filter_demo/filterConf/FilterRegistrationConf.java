package com.example.maven.springbootlearn.filter_demo.filterConf;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.Filter;

import com.example.maven.springbootlearn.filter_demo.filter.FilterDemo;
import com.example.maven.springbootlearn.filter_demo.filter.FilterDemo2;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

/**
 * 管理注册的Filter
 */
@Configuration
public class FilterRegistrationConf {

    @Order(1)
    @Bean("filterDemo")
    public FilterRegistrationBean<FilterDemo> registerFilterDemo() {
        FilterRegistrationBean<FilterDemo> filter = new FilterRegistrationBean<FilterDemo>();
        filter.setFilter(new FilterDemo());
        List<String> pattern = new ArrayList<>();
        pattern.add("/filter/*");
        filter.setUrlPatterns(pattern);
        return filter;
    }

    @Order(1)
    @Bean("filterDemo2")
    public FilterRegistrationBean<Filter> registerFilterDemo2() {
        FilterRegistrationBean<Filter> filter = new FilterRegistrationBean<>();
        filter.setFilter(new FilterDemo2());
        List<String> pattern = new ArrayList<>();
        pattern.add("/filter/demo2/*");
        filter.setUrlPatterns(pattern);
        return filter;
    }
}
