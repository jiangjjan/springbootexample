package com.example.maven.springbootlearn.redis_demo.service;

import java.util.List;

import javax.annotation.Resource;

import com.example.maven.springbootlearn.mybatis_demo.entity.Student;
import com.example.maven.springbootlearn.mybatis_demo.mapper.IStudentMapper;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
//cacheNames用于定义缓存的分组，在这个类里进行操作的缓存会存放在对应的分组下面
@CacheConfig(cacheNames="studentsgroup")
public class QueryStudentService {

    @Resource
    IStudentMapper studentMapper;

    @Cacheable("students")
    public List<Student> getStudentS(){
        System.out.println("select Student");
        return studentMapper.selectStudent(new Student());
    }

    @CachePut(key = "#p0.name")
    @Transactional(rollbackFor = RuntimeException.class)
    public int addStudent(Student s){
        System.out.println("insert Student");
        return studentMapper.insertStudent(s);
    }

}
