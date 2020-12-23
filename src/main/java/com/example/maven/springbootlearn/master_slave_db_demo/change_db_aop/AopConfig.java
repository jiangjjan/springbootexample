package com.example.maven.springbootlearn.master_slave_db_demo.change_db_aop;

import com.example.maven.springbootlearn.master_slave_db_demo.dbconf.RoutingDataSource;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

@Component
@Aspect
@ConditionalOnProperty("doubledb.enable")
public class AopConfig {

    @Around("@annotation(master)")
    public Object master(ProceedingJoinPoint proc, Master master) throws Throwable {

        RoutingDataSource.switchDataSource("master");
        try {
            return proc.proceed();
        } finally {
            RoutingDataSource.clear();
        }

    }

    @Around("@annotation(slave)")
    public Object slave(ProceedingJoinPoint proc, Slave slave) throws Throwable {

        RoutingDataSource.switchDataSource("slave");
        try {
            return proc.proceed();
        } finally {
            RoutingDataSource.clear();
        }
    }

}
