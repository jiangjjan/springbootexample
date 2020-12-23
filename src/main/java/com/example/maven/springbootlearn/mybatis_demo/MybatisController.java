package com.example.maven.springbootlearn.mybatis_demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.maven.springbootlearn.master_slave_db_demo.change_db_aop.Master;
import com.example.maven.springbootlearn.mybatis_demo.entity.Student;
import com.example.maven.springbootlearn.mybatis_demo.mapper.IStudentMapper;

@RestController
@RequestMapping("mybatis")
public class MybatisController {

    private IStudentMapper studentMapper;

    @RequestMapping("student")
    @Master
    public Object queryStudent(@RequestBody Student student){

       return studentMapper.selectStudent(student);
    }
    
    @Autowired
    public void setIStudentMapper(IStudentMapper studentMapper){
        this.studentMapper=studentMapper;
    }
        
}
