package com.example.maven.springbootlearn.master_slave_db_demo;

import com.example.maven.springbootlearn.master_slave_db_demo.change_db_aop.Slave;
import com.example.maven.springbootlearn.mybatis_demo.entity.Student;
import com.example.maven.springbootlearn.mybatis_demo.mapper.IStudentMapper;
import com.zaxxer.hikari.HikariConfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("db")
@ConditionalOnProperty("doubledb.enable")
public class DBSourceController {

    @Autowired 
    @Qualifier("masterDataSourceProperties") 
    HikariConfig masterConf;
    @GetMapping("masterPro")
    public Object getDBProperties(){
        return masterConf;
    }


    //未标注的使用默认数据源
    @Autowired
    IStudentMapper studentMapper;
    @RequestMapping("student")
    public Object masterDbTest(@RequestBody Student student){
        return studentMapper.selectStudent(student);
    }

    //使用从数据库
    @Slave
    @RequestMapping("slave/student")  
    public Object slaveDbTest(@RequestBody Student student){
        return studentMapper.selectStudent(student);
    }
}
