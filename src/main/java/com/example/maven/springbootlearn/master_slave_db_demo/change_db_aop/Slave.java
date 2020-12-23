package com.example.maven.springbootlearn.master_slave_db_demo.change_db_aop;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target( ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Slave {
    String value() default "";
}
