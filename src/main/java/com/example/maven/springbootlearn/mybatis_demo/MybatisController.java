package com.example.maven.springbootlearn.mybatis_demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

import com.example.maven.springbootlearn.master_slave_db_demo.change_db_aop.Master;
import com.example.maven.springbootlearn.mybatis_demo.entity.Student;
import com.example.maven.springbootlearn.mybatis_demo.mapper.IStudentMapper;

@RestController
@RequestMapping("mybatis")
@Slf4j
public class MybatisController {

    private IStudentMapper studentMapper;

    @GetMapping("student/{name}")
    @Master
    public Object queryStudent(@PathVariable String name){
            log.info(name);
       return studentMapper.selectStudent(Student.builder().name(name).build());
    }
    

    @PostMapping("student")
    @Master
    public Object addStudent(@RequestBody Student student){
            log.info("{}",student);
       return studentMapper.insertStudent(student);
    }
   
   
    @Autowired
    public void setIStudentMapper(IStudentMapper studentMapper){
        this.studentMapper=studentMapper;
    }
        
}
